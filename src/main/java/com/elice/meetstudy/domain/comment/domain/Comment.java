package com.elice.meetstudy.domain.comment.domain;

import com.elice.meetstudy.domain.comment.dto.CommentEditor;
import com.elice.meetstudy.domain.post.domain.Post;
import com.elice.meetstudy.domain.user.domain.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  comment Entity
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String content;

  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @Builder
  public Comment(Post post, User user, String content) {
    this.post = post;
    this.user = user;
    this.content = content;
  }

  public CommentEditor.CommentEditorBuilder toEditor() {
    return CommentEditor.builder().content(this.content);
  }

  public void edit(CommentEditor commentEditor) {
    this.content = commentEditor.getContent();
  }

}