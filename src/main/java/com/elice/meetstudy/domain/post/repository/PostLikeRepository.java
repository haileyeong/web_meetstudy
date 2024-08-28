package com.elice.meetstudy.domain.post.repository;

import com.elice.meetstudy.domain.post.domain.PostLike;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

  // 게시글 좋아요

  @Modifying
  @Query("DELETE FROM PostLike pl WHERE pl.user.id = :userId AND pl.post.id = :postId")
  void deleteByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<PostLike> findByUserIdAndPostId(
      @Param("userId") Long userId, @Param("postId") Long postId);

  @Query("SELECT COUNT(pl.id) FROM PostLike pl WHERE pl.post.id = :postId")
  long countByPostId(@Param("postId") Long postId);
}
