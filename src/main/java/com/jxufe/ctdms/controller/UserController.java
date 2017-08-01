package com.jxufe.ctdms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dto.AjaxResult;
import com.jxufe.ctdms.service.UserService;
 

@Controller
public class UserController { 
	  
	@Autowired
	UserService userService;
	@RequestMapping(value="login") 
	public String login(){ 
		System.out.println("login");
		return "";
	 }  
	/**
	 * 用户登录 
	 */
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public ModelAndView loginPage(
			@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelv = new ModelAndView();
		if (error != null) {
				modelv.addObject("error", "请确认您所输入的ctdms ID,姓名以及密码是否正确!");
			}else if (logout != null) {
				modelv.addObject("logout", "退出登录成功");
			}
		modelv.setViewName("login");
		return modelv;
	}
	/**
	 *  退出登录
	 */
	@RequestMapping(value = "{userId}/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	/**
	 *  获得用户管理界面
	 */
	@RequestMapping(value = "{userId}/users", method = RequestMethod.GET)
	public String usersPage(@PathVariable("userId")long userId,
			Model model) { 
		model.addAttribute("users", userService.findAll()); 
		return "users";
	}
	/**
	 *  新增用户
	 */
	@RequestMapping(value = "{userId}/users", method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResult<String> addUser(@PathVariable("userId")long userId) {
		
		return new AjaxResult<>("success");
	}
	/**
	 *  禁止或启用  用户
	 */
	@RequestMapping(value = "{userId}/user/forbid", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult<String> banUser(@PathVariable("userId")long userId,
			@RequestParam(value="id",required=true)long uid,
			@RequestParam(value="forbid",required=true)int forbid) {
		userService.forbid(uid,forbid);
		return new AjaxResult<>("success");
	}
	/**
	 *  删除用户
	 */
	@RequestMapping(value = "{userId}/users", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResult<String> deleteUser(@PathVariable("userId")long userId,
			@RequestParam(value="id",required=true)long uid) {
		userService.delete(uid);
		return new AjaxResult<>("success");
	}
	/**
	 *  修改用户信息
	 */
	@RequestMapping(value = "{userId}/user/info", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult<String> updateUser(@PathVariable("userId")long userId,
			@RequestParam(value="id",required=true)long uid,
			@RequestParam(value="user",required=true)User user) {
		userService.modifyInfo(uid,user); 
		return new AjaxResult<>("success");
	}
	
	/**
	 *  修改用户密码
	 */
	@RequestMapping(value = "{userId}/user/pass", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult<String> passUser(@PathVariable("userId")long userId,
			@RequestParam(value="id",required=true)long uid,
			@RequestParam(value="pass",required=true)String passWord) {
		userService.modifyPassword(uid,passWord); 
		return new AjaxResult<>("success");
	}
	/**
	 * 注册后自动登录
	 */
	@Autowired
	@Qualifier("org.springframework.security.authenticationManager") 
	protected AuthenticationManager authenticationManager; 
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("user") User user,
			
			HttpServletRequest request) { 
		String passWord = user.getPassWord();
		try{ 
			userService.register(user);
			 
		}catch(Exception e){
			System.err.println("[注册失败]尝试注册账号:"+user.getUserName());
			return new ModelAndView(new RedirectView("login?error"));
		} 
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getUserName(),passWord);
		 
		try {
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager
					.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(
					authenticatedUser);
			request.getSession()
					.setAttribute(
							HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
							SecurityContextHolder.getContext());
		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			return new ModelAndView(new RedirectView("login"));
		} 
		return new ModelAndView(new RedirectView("login"));
	}
	
	
} 
