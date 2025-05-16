package com.example.korea_sleepTech_test02.repository;

import com.example.korea_sleepTech_test02.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
