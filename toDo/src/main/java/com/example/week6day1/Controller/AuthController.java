package com.example.week6day1.Controller;

import com.example.week6day1.Model.Myuser;
import com.example.week6day1.Service.AuthService;
import com.example.week6day1.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(authService.getAll());
    }
    @GetMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login");
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Myuser myuser){
        authService.register(myuser);
        return ResponseEntity.status(200).body("user registered");
    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }
    @GetMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("welcome admin");
    }

    @GetMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("welcome User");
    }
}
