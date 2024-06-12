package com.gridsocial.controller;


import org.springframework.ui.Model;

import com.gridsocial.model.Notification;
import com.gridsocial.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public String getAllNotifications(Model model) {
        List<Notification> notifications = notificationService.getAllNotifications();
        model.addAttribute("notifications", notifications);
        return "/personal-view/personal-view";  // Thymeleaf template name (notifications.html)
    }

    @PostMapping
    public String createNotification(@RequestParam String message, @RequestParam String recipient, Model model) {
        notificationService.createNotification(message, recipient);
        return "redirect:/notifications";
    }
}
