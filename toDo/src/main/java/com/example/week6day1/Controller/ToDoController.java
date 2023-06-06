package com.example.week6day1.Controller;

import com.example.week6day1.Model.Myuser;
import com.example.week6day1.Model.ToDo;
import com.example.week6day1.Service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;
    @GetMapping("/get")
    public ResponseEntity getTodos(@AuthenticationPrincipal Myuser myuser){

        List<ToDo> toDoList = toDoService.getTodo(myuser.getId());
        return ResponseEntity.status(200).body(toDoList);

    }
    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal Myuser myuser, @RequestBody ToDo toDo){
        toDoService.addTodo(myuser.getId(),toDo);
        return ResponseEntity.status(200).body("to do added");


    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal Myuser myuser,@RequestBody ToDo toDo, @PathVariable Integer id){
        toDoService.updateTodo(myuser.getId(),toDo,id);
        return ResponseEntity.status(200).body("to do updated");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal Myuser myuser,@PathVariable Integer id){
        toDoService.deleteTodo(myuser.getId(),id);
        return ResponseEntity.status(200).body("to do deleted");

    }


}
