package com.example.week6day1.Service;

import com.example.week6day1.Model.Myuser;
import com.example.week6day1.Repository.MyuserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MyuserRepository myuserRepository;

    public List<Myuser> getAll(){
        return myuserRepository.findAll();
    }
    public void register(Myuser myuser){
        String hash= new BCryptPasswordEncoder().encode(myuser.getPassword());
        myuser.setPassword(hash);
        myuser.setRole("USER");
        myuserRepository.save(myuser);
    }
}
