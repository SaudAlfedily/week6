package com.example.week6day1.Repository;

import com.example.week6day1.Model.Myuser;
import com.example.week6day1.Model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findToDoByUserId(Integer id);

    ToDo findToDoById(Integer id);


}
