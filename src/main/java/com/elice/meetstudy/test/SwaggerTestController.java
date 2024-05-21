package com.elice.meetstudy.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "게시판", description = "게시판 API 명세서입니다.")
public class SwaggerTestController {

  @Operation(
      summary = "게시글 작성",
      description = "게시글을 작성할까요?.?"
  )
  @ApiResponse(
      responseCode = "200",
      description = "게시글을 작성했습니다."
  )
  @GetMapping("/test")
  public String testSwagger() {
    return "TEST";
  }


}