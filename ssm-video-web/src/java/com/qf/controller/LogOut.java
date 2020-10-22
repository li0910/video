package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("exit")
public class LogOut {

    @RequestMapping("logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/course.jsp";
    }

    @RequestMapping("outLine")
    public String outLine(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/index.jsp";
    }
}
