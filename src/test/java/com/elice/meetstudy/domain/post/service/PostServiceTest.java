package com.elice.meetstudy.domain.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.elice.meetstudy.domain.category.entity.Category;
import com.elice.meetstudy.domain.category.repository.CategoryRepository;
import com.elice.meetstudy.domain.post.domain.Post;
import com.elice.meetstudy.domain.post.dto.PostResponseDTO;
import com.elice.meetstudy.domain.post.dto.PostWriteDTO;
import com.elice.meetstudy.domain.post.repository.PostRepository;
import com.elice.meetstudy.domain.user.domain.Role;
import com.elice.meetstudy.domain.user.domain.User;
import com.elice.meetstudy.domain.user.repository.UserRepository;
import com.elice.meetstudy.util.EntityFinder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PostServiceTest {

  @Mock private PostRepository postRepository;

  @Mock private UserRepository userRepository;

  @Mock private CategoryRepository categoryRepository;

  @Mock private EntityFinder entityFinder;

  @InjectMocks private PostService postService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  // Given
  Long userId = 1L;
  Long categoryId = 2L;
  String title = "테스트 제목";
  String content = "테스트 내용";
  LocalDateTime time = LocalDateTime.now();
  User user = new User("test@test.com", "password", "username", "닉넴", time, Role.USER, userId);
  Category category = new Category("카테고리 이름", "카테고리 설명");
  PostWriteDTO postCreate = new PostWriteDTO(categoryId, title, content);

  @Test
  @DisplayName("게시글 작성 테스트")
  void 게시글작성() {

    // EntityFinder가 유저와 카테고리를 찾을 때 리턴할 값 설정
    when(entityFinder.getUser()).thenReturn(user);

    when(entityFinder.findCategoryById(categoryId)).thenReturn(category);

    // When
    Post newPost =
        Post.builder().user(user).category(category).title(title).content(content).build();
    Post savedPost = newPost;

    when(postRepository.save(any(Post.class))).thenReturn(savedPost);

    PostResponseDTO postResponse = postService.write(postCreate);

    // Then
    assertNotNull(postResponse);
    assertEquals(title, postResponse.getTitle());
    assertEquals(content, postResponse.getContent());
  }

  @Test
  @DisplayName("특정 게시판에서 게시글 작성")
  void 게시판에서_글_작성() {
    when(entityFinder.getUser()).thenReturn(user);

    // When
    Post newPost = Post.builder().build();
  }
}
