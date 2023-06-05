package com.example.homework27.Controller;

import com.example.homework27.Model.Blog;
import com.example.homework27.Model.User;
import com.example.homework27.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity getBlogs(@AuthenticationPrincipal User user) {

        List<Blog> blogList = blogService.getBlog(user.getId());
        return ResponseEntity.status(200).body(blogList);

    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("blog added");


    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal User user, @RequestBody Blog blog, @PathVariable Integer id) {
        blogService.updateBlog(user.getId(), blog, id);
        return ResponseEntity.status(200).body("blog updated");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        blogService.deleteBlog(user.getId(), id);
        return ResponseEntity.status(200).body("blog deleted");

    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getBlogById(@PathVariable @Valid Integer id) {
        Blog blog = blogService.getBlogById(id);
        return ResponseEntity.status(200).body(blog);

    }

    @GetMapping("/get-title/{title}")

    public ResponseEntity getBlogByTitle(@PathVariable String title) {
        List<Blog> blog = blogService.getBlogByTitle(title);
        return ResponseEntity.status(200).body(blog);
    }

    @GetMapping("/get-users-blogs")
    public ResponseEntity getAllUsersBlogs(){
      List<Blog> blogs= blogService.getAllUsersBlogs();
        return ResponseEntity.status(200).body(blogs);
    }
}
