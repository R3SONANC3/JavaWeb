package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        // ดึงข้อมูลทั้งหมดจากฐานข้อมูลและส่งไปยังหน้า home.html
        model.addAttribute("users", userRepository.findAll());
        return "home"; // ส่งไปที่หน้า home.html
    }
}
