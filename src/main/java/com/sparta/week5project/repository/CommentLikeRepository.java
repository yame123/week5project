package com.sparta.week5project.repository;

import com.sparta.week5project.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long>{
}