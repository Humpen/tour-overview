package de.hsbhv.touroverview.backend.vuforia;

import de.hsbhv.touroverview.backend.entities.Response;
import de.hsbhv.touroverview.backend.entities.Vuforia;
import de.hsbhv.touroverview.backend.entities.Vuforias;
import de.hsbhv.touroverview.backend.graphql.MessageUtil;
import de.hsbhv.touroverview.backend.graphql.QueryManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class AsyncCaller {
    @Async("taskExecuter")
    public void updateVuforiaTargets() {
        UpdateTask updateTask = new UpdateTask();
        updateTask.deleteVuforiaTargets();
        updateTask.updateTargets();
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        updateVuforia();
    }

    @Async("taskExecuter")
    public void updateVuforia() {
        Timer timer = new Timer();
        timer.schedule(new UpdateTask(), 86400000); // Update every 24 hours
    }

    class UpdateTask extends TimerTask {

        @Override
        public void run() {
            deleteVuforiaTargets();
            updateTargets();
            updateVuforia();
        }

        public void updateTargets() {
            Vuforias vuforias = QueryManager.mapJsonToObject(QueryManager.getVuforiasEasy(), Vuforias.class);
            vuforias.getVuforias();
            for (Vuforia vuforia : vuforias.getVuforias()) {
                try {
                    FileUtils.copyURLToFile(new URL(vuforia.getImage().getImageUrl()), new File(vuforia.getName() + ".png"));
                    vuforia.getImage().image = FileUtils.getFile(vuforia.getName() + ".png");
                    new PostNewTarget("037b8b16480313b190148e80829cd3bea027b469", "748f8961034e7dc36b0c72b670796c95c90f6fbc",
                            vuforia.getName(), vuforia.getImage().image.getPath(), vuforia.getTourId().getId()).postTargetThenPollStatus();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for (Vuforia vuforia : vuforias.getVuforias()) {
                vuforia.getImage().getImage().delete();
                vuforia.getImage().image = null;
            }
        }

        public void deleteVuforiaTargets() {
            GetAllTargets getAllTargets = new GetAllTargets("037b8b16480313b190148e80829cd3bea027b469", "748f8961034e7dc36b0c72b670796c95c90f6fbc");

            try {
                String response = IOUtils.toString(getAllTargets.getTargets().getEntity().getContent(), StandardCharsets.UTF_8);
                System.out.println(response);
                Response responseObject = MessageUtil.createFromJson(response, Response.class);
                System.out.println(response);
                if (!responseObject.results.isEmpty()) {
                    DeleteTarget target = null;
                    for (String targetId : responseObject.results) {
                        System.out.println("Target ID: " + targetId);
                        target = new DeleteTarget("037b8b16480313b190148e80829cd3bea027b469", "748f8961034e7dc36b0c72b670796c95c90f6fbc", targetId);
                        target.deactivateThenDeleteTarget();
                    }
                    while (!target.getDeleted()) {
                        Thread.sleep(360000); // Warte 6 Minuten bevor das Flag erneut abgefragt wird
                    }
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
