package com.elice.meetstudy.domain.post.service;

import com.elice.meetstudy.domain.post.domain.Post;
import com.elice.meetstudy.domain.post.domain.PostLike;
import com.elice.meetstudy.domain.post.repository.PostLikeRepository;
import com.elice.meetstudy.util.EntityFinder;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostLikeService {

  private final PostLikeRepository postLikeRepository;
  private final EntityFinder entityFinder;

  public boolean insert(Long postId) {
    Post post = entityFinder.findPost(postId);
    Optional<PostLike> postLike = entityFinder.findLike(post.getId());

    PostLike newLike = PostLike.builder().post(post).user(entityFinder.getUser()).build();
    if (postLike.isPresent()) {
      return false;
    }
    postLikeRepository.save(newLike);
    return true;
  }

  public void delete(Long postId) {
    postLikeRepository.deleteByUserIdAndPostId(entityFinder.getUser().getId(), postId);
  }

  public Long getLikeNumByPost(Long postId) {
    return postLikeRepository.countByPostId(postId);
  }
}
