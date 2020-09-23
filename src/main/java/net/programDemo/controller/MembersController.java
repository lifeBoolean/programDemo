package net.programDemo.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.programDemo.dao.MembersDao;
import net.programDemo.demo.HomeController;
import net.programDemo.model.MembersVo;
import net.programDemo.service.MembersServiceImpl;

@Controller
@RequestMapping("/member")
public class MembersController {
	@Inject
	MembersServiceImpl membersService;
	
	private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String insertForm() throws Exception {
		
		return "/member/registerForm";		
	}
	
	@RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
	public String joinProcess(MembersVo membersVo) throws Exception {
		
		membersService.memberInsert(membersVo);
		
		return "redirect:/member/result";
	}
	
	@RequestMapping(value = "/result")
	public String memberResult() {
		
		return "/member/result";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws Exception {
		
		return "/member/loginForm";
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(HttpServletRequest request, RedirectAttributes rttr, MembersVo membersVo) throws Exception {
		
		HttpSession session = request.getSession();
		
		
		MembersVo getMember = membersService.memberLogin(membersVo);
		
		System.out.println("getMember: " + getMember);		
		logger.info("geMember logger: " + getMember);
		
		if(getMember == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		} else {
			session.setAttribute("member", getMember);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutProcess(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify() throws Exception {
		
//		MembersVo getMember = membersService.memberLogin(membersVo);
//		
//		model.addAttribute("member", getMember);
		
		return "/member/modifyForm";
	}
	
	@RequestMapping(value = "/modifyProcess", method = RequestMethod.POST)
	public String modifyProcess(MembersVo membersVo) throws Exception {
		
		membersService.memberModify(membersVo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete")
	public String delete() {
		
		return "/member/deleteForm";
	}
	
	@RequestMapping(value = "deleteProcess", method = RequestMethod.POST)
	public String deleteProcess(MembersVo membersVo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		HttpSession session = request.getSession();
		
		int n = membersService.memberDelete(membersVo);
		
		logger.info("n : ==================== : " + n);		
		
		if(n >= 1) {
			session.invalidate();
		} else {
			rttr.addFlashAttribute("msg", false);
		}
		
		return "redirect:/";
	}

}
