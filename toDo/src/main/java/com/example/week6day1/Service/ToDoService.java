package com.example.week6day1.Service;

import com.example.week6day1.Model.Myuser;
import com.example.week6day1.Model.ToDo;
import com.example.week6day1.Repository.MyuserRepository;
import com.example.week6day1.Repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final MyuserRepository myuserRepository;
    public List<ToDo> getTodo(Integer id) {
        List<ToDo> toDoList=toDoRepository.findToDoByUserId(id);
        return toDoList;
    }

    public void addTodo(Integer userid, ToDo toDo) {
        Myuser myuser=myuserRepository.findMyuserById(userid);
        toDo.setUser(myuser);
        toDoRepository.save(toDo);
    }

    public void updateTodo(Integer userid, ToDo toDo, Integer id) {
        ToDo oldTod=toDoRepository.findToDoById(id);
        Myuser myuser=myuserRepository.findMyuserById(userid);
        if (oldTod.getUser().getId()!=userid||oldTod==null){

            throw new RuntimeException("oldTod.getUserId()!=userid && oldTod==null");


        }
        toDo.setUser(myuser);
        oldTod.setMassage(toDo.getMassage());
        toDoRepository.save(toDo);



    }

    public void deleteTodo(Integer userid, Integer id) {
        ToDo oldTod=toDoRepository.findToDoById(id);
        if (oldTod.getUser().getId()!=userid||oldTod==null){

            throw new RuntimeException("oldTod.getUserId()!=userid && oldTod==null");


        }
        toDoRepository.delete(oldTod);
    }
}
