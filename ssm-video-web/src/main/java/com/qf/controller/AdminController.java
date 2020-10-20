package com.qf.controller;

import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //    通过此方法跳转到登录界面
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/behind/login.jsp";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, Admin data) {


        Admin admin = adminService.findAdmin(data);

        if (admin != null) {

            HttpSession session = request.getSession(true);
            session.setAttribute("admin", admin);
            return "success";
        } else {

            return "failed";
        }

    }

    @RequestMapping("/toEdit")
    public String toEdit() {

        return "/behind/adminMes.jsp";
    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return("/behind/login.jsp");
    }

    @SneakyThrows
    @RequestMapping("/edit")
    public void edit(Admin admin,HttpServletRequest request, HttpServletResponse response) {
        adminService.updateAdmin(admin);

        request.getSession(false).invalidate();
        request.getSession(true).setAttribute("admin",admin);
        response.sendRedirect("/video/list");
    }
}
