package com.example.homework27.Controller;

import com.example.homework27.Model.User;
import com.example.homework27.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //admin
    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(authService.getAll());

    }

    //add user
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User myuser){
        authService.register(myuser);
        return ResponseEntity.status(200).body("user registered");
    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }


    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @RequestBody@Valid User user1){
        authService.updateUser(user.getId(),user1);
        return ResponseEntity.status(200).body(" User updated");
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user){
        authService.deleteUser(user.getId());
        return ResponseEntity.status(200).body(" User deleted");
    }
    @DeleteMapping("/deletebyadmin/{userid}")
    public ResponseEntity deleteUserByAdmin(@PathVariable Integer userid){
        authService.deleteUserByAdmin(userid);
        return ResponseEntity.status(200).body(" User deleted");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
       User use= authService.getUserById(id);
        return ResponseEntity.status(200).body(use);

    }
}
