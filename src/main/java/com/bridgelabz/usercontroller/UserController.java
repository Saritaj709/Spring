package com.bridgelabz.usercontroller;

import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;  

import com.bridgelabz.model.User;

@RestController
public class UserController {	
	    @RequestMapping("/")  
	    public String index(){  
	        return"index";  
	    }  
	    @RequestMapping(value="/save", method=RequestMethod.POST)  
	    public ModelAndView save(@ModelAttribute User user){  
	        ModelAndView modelAndView = new ModelAndView();  
	modelAndView.setViewName("userdata");      
	modelAndView.addObject("user", user);    
	return modelAndView;  
	    }  
}
