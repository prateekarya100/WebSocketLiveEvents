package com.nmap.nMapScanner.websocketPkg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        Map<String, String> dummyScanData = new HashMap<>();
        dummyScanData.put("profile1", "192.168.0.1");
        dummyScanData.put("profile2", "192.168.0.2");
        model.addAttribute("scanData", dummyScanData);
        return "dashboard";
    }
}
