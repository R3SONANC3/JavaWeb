package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository; // Repository สำหรับจัดการข้อมูลผู้ใช้

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    // ฟอร์มสำหรับเพิ่ม User
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user"; // ส่งไปยังหน้า add-user.html
    }

    // ฟอร์มสำหรับบันทึกข้อมูล User
    @PostMapping("/add-user")
    public String addUser(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user); // บันทึกข้อมูลลงในฐานข้อมูล
        return "redirect:/"; // เปลี่ยนเส้นทางไปที่หน้าหลักหลังจากเพิ่ม user
    }
}
