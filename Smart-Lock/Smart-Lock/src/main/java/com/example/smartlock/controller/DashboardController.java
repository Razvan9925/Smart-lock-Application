package com.example.smartlock.controller;

import com.example.smartlock.entity.Door;
import com.example.smartlock.service.DoorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final DoorService doorService;


    @GetMapping("")
    public String dashboard() {
        return "dashboard/dashboard";
    }

    @GetMapping("/close")
    public String close() {

        doorService.updateDoor(false);
        System.out.println("Close ?");
        return "redirect:/dashboard";
    }

    @GetMapping("/open")
    public String open() {

        doorService.updateDoor(true);
        System.out.println("Open ?");
        return "redirect:/dashboard";
    }







}
