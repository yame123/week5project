package com.sparta.week5project.repository;

import com.sparta.week5project.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike,Long>{
    }

