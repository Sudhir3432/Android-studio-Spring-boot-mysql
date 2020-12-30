package com.test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/demo")
public class MainController {
   @Autowired
 private MainService mainService;
    
    @GetMapping("/tests")
    public String  test1() {
    	return "working fine";
    }
  
    @PostMapping("/add")
    public @ResponseBody boolean add( @RequestParam String fname,@RequestParam String lname)
    {
        return mainService.add(fname,lname);
    }

   
 
}
