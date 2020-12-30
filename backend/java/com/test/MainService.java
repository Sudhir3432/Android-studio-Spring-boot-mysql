package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






@Service
public class MainService {
    @Autowired
    private UserRepository userRepository;

   
   
    public boolean add(String fname,String lname)
    {
        User user = new User();
        user.setFname(fname);
        user.setLname(lname);
        userRepository.save(user);
        return true;
    }
   

}
