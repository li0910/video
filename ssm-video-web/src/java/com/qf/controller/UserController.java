package com.qf.controller;

import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.ImageCut;
import com.qf.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.qf.utils.MailUtils.getValidateCode;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    跳转个人信息页面
    @RequestMapping("/showMyProfile")
    public String showMyProfile() {
        return "/myProfile.jsp";
    }

//    跳转修改信息页面
    @RequestMapping("changeProfile")
    public String changeProfile() {
        return "/change_profile.jsp";
    }

//    修改个人信息
    @RequestMapping("updateUser")
    public String update(User user, Model model) {

        userService.update(user);
        model.addAttribute("user",user);
        System.out.println(user);
        return "/myProfile.jsp";
    }

//  登录测试方法
    @RequestMapping("login")
    public String test() {
        return "/index.jsp";
    }

//    登录方法
    @RequestMapping("loginUser")
    @ResponseBody
    public String login(User params, HttpServletRequest request) {

//        System.out.println(params);
        User user = userService.findByEmail(params);


        if (user != null) {
            request.getSession().setAttribute("user",user);
            return "success";
        } else {
            return "false";
        }
    }

//    用户注册
    @RequestMapping("insertUser")
    @ResponseBody
    public String insertUser(User user) {

        userService.addUser(user);

        return "success";
    }

//    邮箱验证
    @RequestMapping("validateEmail")
    @ResponseBody
    public String validateEmail(String email) {

        User user = userService.checkEmail(email);

        if (user == null) {
            return "success";
        } else {
            return "false";
        }
    }

//    跳转上传头像页面
    @RequestMapping("changeAvatar")
    public String changeAvatar() {

        return "/change_avatar.jsp";
    }

    //    上传头像方法
    @RequestMapping("upLoadImage")
    public String upLoadImage(Integer id, MultipartFile image_file, Model model,String x1, String y1, String x2, String y2) {
        System.out.println(x1);
        int x1Int = (int) Double.parseDouble(x1);
        int x2Int = (int) Double.parseDouble(x2);
        int y1Int = (int) Double.parseDouble(y1);
        int y2Int = (int) Double.parseDouble(y2);

        User user = userService.findAll(id);
        System.out.println(user);

        String path = "D:\\study tools\\stage 2\\tomcat_download\\apache-tomcat-8.5.41\\webapps\\upload\\";

        ImageCut imageCut = new ImageCut();

        String originalFilename = image_file.getOriginalFilename();

        originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        originalFilename = uuid + originalFilename;


        System.out.println("上传的文件名：" + originalFilename);

        System.out.println(originalFilename);
        File file = new File(path + originalFilename);

        try {
            image_file.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageCut.cutImage(path + "/" + originalFilename, x1Int, y1Int, x2Int - x1Int ,y2Int - y1Int);

        user.setImgurl(originalFilename);
        model.addAttribute("user", user);
        System.out.println(user.getId());
        System.out.println(user);
        userService.update(user);
        return "/myProfile.jsp";
    }

//    跳转修改密码页面
    @RequestMapping("passwordSafe")
    public String passwordSafe() {
        return "/password_safe.jsp";
    }

//    验证密码是否一致
    @RequestMapping("validatePassword")
    @ResponseBody
    public String validatePassword(HttpServletRequest request,String password) {

        User user = (User) request.getSession().getAttribute("user");
        if (password.equals(user.getPassword())) {
            return "success";
        } else {
            return "false";
        }
    }

    //    修改密码方法
    @RequestMapping("updatePassword")
    public String updatePassword(Integer id, Model model, String newPassword) {

        System.out.println(newPassword);
        User user = userService.findAll(id);
        user.setPassword(newPassword);
        System.out.println(user);
        model.addAttribute("user", user);
        userService.update(user);

        return "/myProfile.jsp";
    }

    String validateCode = "";

    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(HttpServletRequest request, String email, Model model) {
        User user = userService.checkEmail(email);
        if (user != null) {
            model.addAttribute("email", email);
            validateCode =  getValidateCode(6);
            MailUtils.sendMail(email, "你好，这是一封测试邮件，无需回复。", "测试邮件随机生成的验证码是：" + validateCode);
            System.out.println("发送成功");
            return "success";
        } else {
            return "hasNoUser";
        }
    }

    @RequestMapping("forgetPassword")
    public String forgetPassword() {
        return "/forget_password.jsp";
    }

    @RequestMapping("validateEmailCode")
    public String validateEmailCode(String code, String email, Model model) {
        System.out.println(code);
        System.out.println(validateCode);
        if (code.equals(validateCode)) {
            model.addAttribute("email", email);
            return "/reset_password.jsp";
        } else {
            return "/forget_password.jsp";
        }
    }

    @RequestMapping("resetPassword")
    public String resetPassword(String email, String password) {
        System.out.println(password);
        System.out.println(email);
        User user = userService.checkEmail(email);
        user.setPassword(password);
        System.out.println(user);
        userService.update(user);

        return "/index.jsp";
    }
}
