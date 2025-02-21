package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //여기가 컨트롤러다! 표시
public class WelcomeController {
	//기본 매핑 경로
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home() {
        return "redirect:/home";  // /home으로 리다이렉트
    }
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to BookMarket");
		model.addAttribute("strapline", "Welcome to Web Shopping Mall!");
		return "welcome";
	}
}
