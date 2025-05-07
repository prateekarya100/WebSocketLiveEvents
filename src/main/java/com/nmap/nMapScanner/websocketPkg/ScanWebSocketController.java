package com.nmap.nMapScanner.websocketPkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScanWebSocketController {

        @Autowired
        private SimpMessagingTemplate messagingTemplate;

        @PostMapping("/scan/rescan")
        @ResponseBody
        public String rescan(@RequestParam String profile, @RequestParam String target) {
            new Thread(() -> {
                try {
                    // Simulate scan process
                    for (int i = 1; i <= 5; i++) {
                        Thread.sleep(1000); // Simulate scanning delay
                        messagingTemplate.convertAndSend("/topic/scan-progress", profile + ": " + (i * 20) + "% complete");
                    }
                    // When the scan is complete, notify the frontend
                    messagingTemplate.convertAndSend("/topic/scan-progress", profile + ": Scan complete");
                } catch (InterruptedException e) {
                    // If there's an error, notify the frontend
                    messagingTemplate.convertAndSend("/topic/scan-progress", profile + ": Scan failed");
                }
            }).start();
            return "OK";
        }

}
