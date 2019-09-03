package com.nasim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nasim.model.Mail;
import com.nasim.service.MailService;

@Controller
public class MailController {
	@Autowired
	private MailService mailService;

	@RequestMapping(value= "/", method=RequestMethod.GET)
	public String home() {
		return "redirect:sendMail";
	}
	
	@RequestMapping(value= "/sendMail", method=RequestMethod.GET)
	public String sendEmailForm(Model model) {
		Mail mail = new Mail();
		model.addAttribute("sendMailForm", mail);
		return "sendMail";
	}

	@RequestMapping(value="/sendMail", method=RequestMethod.POST)
	public String sendMail(Model model, @ModelAttribute("sendMailForm") Mail mail) {
		try {
			mailService.sendMail(mail);			
		} catch(MailException me) {
			System.out.println(me.getMessage());
			return "redirect:error";
		}
		return "redirect:send";
	}
	
	@RequestMapping(value= "/error", method=RequestMethod.GET)
	public String error() {
		return "error";
	}
	
	@RequestMapping(value= "/send", method=RequestMethod.GET)
	public String sent() {
		return "send";
	}
}
