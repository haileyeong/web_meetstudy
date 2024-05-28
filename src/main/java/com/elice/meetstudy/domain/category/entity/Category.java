package com.elice.meetstudy.domain.category.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 *  category Entity
 */

@Entity
@Getter
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

}
