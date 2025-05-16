package com.example.korea_sleepTech_test02.repository;

import com.example.korea_sleepTech_test02.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
