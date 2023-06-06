package com.example.week6day1.Repository;

import com.example.week6day1.Model.Myuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyuserRepository extends JpaRepository<Myuser,Integer> {
    public Myuser findMyuserByUsername(String username);
    public Myuser findMyuserById(Integer id);
}
