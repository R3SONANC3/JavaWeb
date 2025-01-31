package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // คุณสามารถเพิ่มฟังก์ชันเฉพาะเพื่อค้นหาผู้ใช้ตามเงื่อนไขที่ต้องการได้
}
