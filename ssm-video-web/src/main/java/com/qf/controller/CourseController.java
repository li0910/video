package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Course;
import com.qf.pojo.Subject;
import com.qf.service.CourseService;
import com.qf.service.SubjectService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/showCourseList")
    public String showCourseList(
            @RequestParam(required = false, defaultValue = "1")Integer pageNum,
            @RequestParam(required = false, defaultValue = "8") Integer pageSize,
            Model model) {

        PageHelper.startPage(pageNum, pageSize);
        List<Course> courseList = courseService.findAll(null);
        PageInfo<Course> page = new PageInfo<Course>(courseList);

        model.addAttribute("page",page);
        return "/behind/courseList.jsp";
    }

    @RequestMapping("/addCourse")
    public String addCourse(Model model) {
        List<Subject> subjectList = subjectService.findAll(null);

        model.addAttribute("subjectList",subjectList);
        return "/behind/addCourse.jsp";
    }

    @RequestMapping("/edit")
    public String edit(Integer id,Model model) {
//      查找所有学科
        List<Subject> subjectList = subjectService.findAll(null);

        Course course = courseService.findById(id);

        model.addAttribute("subjectList",subjectList);
        model.addAttribute("course",course);
        return "/behind/addCourse.jsp";
    }

    @SneakyThrows
    @RequestMapping("/saveOrUpdate")
    public void saveOrUpdate(Course course, HttpServletResponse response) {
        if (course.getId() != null) {

            System.out.println(course);
            courseService.updateCourse(course);
        } else {
            courseService.insertCourse(course);
        }

        response.sendRedirect("/course/showCourseList");
    }
}
