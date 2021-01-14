package de.hsbhv.touroverview.backend;

import de.hsbhv.touroverview.backend.entities.Vuforia;
import de.hsbhv.touroverview.backend.entities.Vuforias;
import de.hsbhv.touroverview.backend.graphql.QueryManager;
import de.hsbhv.touroverview.backend.vuforia.DeleteTarget;
import de.hsbhv.touroverview.backend.vuforia.GetAllTargets;
import de.hsbhv.touroverview.backend.vuforia.PostNewTarget;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

@Component
public class AsyncCaller {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        updateVuforia();
    }

    @Async
    public void updateVuforia() {
        Timer timer = new Timer();
        timer.schedule(new UpdateTask(), 86400000); // Update every 24 hours
    }
    
    class UpdateTask extends TimerTask {

        @Override
        public void run() {
            deleteVuforiaTargets();

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
            updateVuforia();
        }

        public void deleteVuforiaTargets() {
            GetAllTargets getAllTargets = new GetAllTargets("037b8b16480313b190148e80829cd3bea027b469", "748f8961034e7dc36b0c72b670796c95c90f6fbc");

            try {
                String response = IOUtils.toString(getAllTargets.getTargets().getEntity().getContent(), StandardCharsets.UTF_8);
                System.out.println(response);
                response = response.split(Pattern.quote("["))[1];
                response = response.split(Pattern.quote("]"))[0];
                List<String> targetList = new ArrayList<String>();
                System.out.println(response);
                if (!response.isEmpty()) {
                    while (response.contains(",")) {
                        String cacheText = response.split(",")[0];
                        targetList.add(cacheText.split("\"")[1]);
                        response = response.split(",")[1];
                    }
                    targetList.add(response.split("\"")[1]);
                    DeleteTarget target = null;
                    for (String targetId : targetList) {
                        System.out.println("Target ID: " + targetId);
                        target = new DeleteTarget("037b8b16480313b190148e80829cd3bea027b469", "748f8961034e7dc36b0c72b670796c95c90f6fbc", targetId);
                        target.deactivateThenDeleteTarget();
                    }
                    while (target != null && !target.getDeleted()) {
                        Thread.sleep(360000); // Warte 6 Minuten bevor das Flag erneut abgefragt wird
                    }
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
