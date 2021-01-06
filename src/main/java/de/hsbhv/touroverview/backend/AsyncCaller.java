package de.hsbhv.touroverview.backend;

import de.hsbhv.touroverview.backend.entities.Vuforia;
import de.hsbhv.touroverview.backend.entities.Vuforias;
import de.hsbhv.touroverview.backend.graphql.QueryManager;
import de.hsbhv.touroverview.backend.vuforia.GetAllTargets;
import org.apache.commons.io.FileUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

@Component
public class AsyncCaller {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        updateVuforia();
        testMethod();
    }

    @Async
    public void updateVuforia() {
        Timer timer = new Timer();
        timer.schedule(new UpdateTask(), 2000);
    }

    public void testMethod() {
        GetAllTargets getAllTargets = new GetAllTargets("037b8b16480313b190148e80829cd3bea027b469", "748f8961034e7dc36b0c72b670796c95c90f6fbc");

//        try {
//          String test =   IOUtils.toString(getAllTargets.getTargets().getEntity().getContent(), StandardCharsets.UTF_8);
//        String test = "{\"transaction_id\":\"7fe6d1629e4441498a71f4a57223fd26\",\"result_code\":\"Success\",\"results\":[\"1cebf9b929104bd6bc45a079af548b1a\", \"abese\"]}";
        String response = "{\"transaction_id\":\"7fe6d1629e4441498a71f4a57223fd26\",\"result_code\":\"Success\",\"results\":[\"1cebf9b929104bd6bc45a079af548b1a\"]}";
        System.out.println(response);
        response = response.split(Pattern.quote("["))[1];
        response = response.split(Pattern.quote("]"))[0];
        List<String> targetList = new ArrayList<String>();
        System.out.println(response);
        while (response.contains(",")) {
            String cacheText = response.split(",")[0];
            targetList.add(cacheText.split("\"")[1]);
            response = response.split(",")[1];
        }
        targetList.add(response.split("\"")[1]);

        for (String targetId : targetList) {
            System.out.println("Target ID: " + targetId);
        }
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    class UpdateTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("ASYNC CALL");
            Vuforias vuforias = QueryManager.mapJsonToObject(QueryManager.getVuforiasEasy(), Vuforias.class);
            vuforias.getVuforias();
            for (Vuforia vuforia : vuforias.getVuforias()) {
                try {
                    FileUtils.copyURLToFile(new URL(vuforia.getImage().getImageUrl()), new File(vuforia.getName() + ".png"));
                    vuforia.getImage().image = FileUtils.getFile(vuforia.getName() + ".png");
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
    }
    //Client Access Key e00be4e285f359d9966710426f7f2ae86f11598b
    //Client Secret Key 246cc86bd0f8bec57719f7cc22f3faf1b002f2ee
    //Secret Access Key 037b8b16480313b190148e80829cd3bea027b469
    //Secret Secret Key 748f8961034e7dc36b0c72b670796c95c90f6fbc
}
