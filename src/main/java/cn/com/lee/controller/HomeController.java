package cn.com.lee.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.com.lee.bean.User;
import cn.com.lee.mapping.UserMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("test")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(Model model){
		model.addAttribute("message","Hello world");
		return "hello";
	}
	
	@RequestMapping(value="/template/{userId}")
	public String templateTest(@PathVariable("userId") String userid,@CookieValue("JSESSIONID") String cookie,Model model){
		System.out.println("current cookie is:"+cookie);
		model.addAttribute("userid", userid);
		return "userid";
	}
	
	
	@RequestMapping("/ajax")
	@ResponseBody
	/**
	 * Ajax test
	 * @param request
	 * @return
	 */
	public Object ajaxTest(HttpServletRequest request){
		List<String> list = new ArrayList<String>();
		list.add("man");
		list.add("woment");
		return list;
	}
	
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * call insert method
	 */
	@RequestMapping("/insert")
	public void callInsertUser(){
		User user = new User();
		user.setId("123456");
		user.setName("jeson");
		user.setPassword("123");
		System.out.println("insert user begin..");
		userMapper.insertUser(user);
		System.out.println("insert user end..");
		
	}
}
