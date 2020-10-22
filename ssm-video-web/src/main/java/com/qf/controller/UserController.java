package com.qf.controller;

import com.mysql.cj.xdevapi.Session;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.videos.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.qf.videos.utils.MailUtils.getValidateCode;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 	validateCode用于储存验证码
	 */
	String validateCode = "";

	@RequestMapping("loginUser")
	@ResponseBody
	public String login(User user, HttpServletRequest request) {
//		System.out.println("进入1" + user); 前端拿到的email
		if (user.getEmail() != null) {
			String email = user.getEmail();
//			System.out.println(email); 后端拿到的email
			User user1 = userService.findByEmail(email);

			if (user1.getPassword().equals(user.getPassword())) {
//				System.out.println("进入最终"); 进入之后返回登录成功信息
				request.getSession().setAttribute("user1", user1);
				return "success";
			} else {
				return "false";
			}
		} else {
			return "false";
		}
	}
	@RequestMapping("validateEmailCode")
	public String validateEmailCode(String email, Model model, String code) {
		model.addAttribute("email", email);
		if (code.equals(validateCode)) {
			return "before/reset_password.jsp";
		} else {
			return "before/forget_password.jsp";
		}
	}

	@RequestMapping("forgetPassword")
	public String modifyPassword() {

		System.out.println("进入修改页面");

		return "before/forget_password.jsp";
	}

	@RequestMapping("sendEmail")
	@ResponseBody
	public String sendEmail(User user, Model model, String code) {
//		前台传过来的email
		String email = user.getEmail();
		System.out.println(email);
		model.addAttribute("email", email);
		User byEmail = userService.findByEmail(email);
//		email1数据库email
		String email1 = byEmail.getEmail();
		if (email1 != null) {
			if (email1.equals(email)) {

				validateCode = getValidateCode(6);
				MailUtils.sendMail(email, "你好，这是一封测试邮件，无需回复。", "测试邮件随机生成的验证码是：" + validateCode);
				System.out.println("发送成功");
				System.out.println(validateCode);
				return "success";
			} {
				return "hasNoUser";
			}
		} else {
			return "hasNoUser";
		}

	}

	@RequestMapping("resetPassword")

	public String resetPassword(String password, String email) {
		User user = userService.findByEmail(email);
		user.setPassword(password);
		System.out.println(user);
		userService.updateUser(user);
		return "before/index.jsp";
	}
	@RequestMapping("goBack")
	public String goBack() {
		return "before/index.jsp";
	}
//	public static void main(String[] args, HttpServletRequest request, HttpSession session) {
//
//		User user = (User) session.getAttribute("user");
//		System.out.println(user);
//	}

}

