package com.qf.controller;

import com.qf.pojo.Course;
import com.qf.pojo.Subject;
import com.qf.pojo.SubjectExample;
import com.qf.pojo.Video;
import com.qf.service.CourseService;
import com.qf.service.SubjectService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {
	Course oneCourse = null;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private VideoService videoService;

	@RequestMapping("course/{id}")
//	course/course
	public String findAllCourse(@PathVariable("id") int id,
	                            Model model) {
		List<Subject> subjectList = subjectService.selectByExample(null);
		model.addAttribute("subjectList", subjectList);

		List<Course> courseList = courseService.findAllCourse(id);

		model.addAttribute("courseList", courseList);

//		for (Course course : courseList) {
//			for (int i = 0; i < courseList.size(); i++) {
//
//				List<Video> videoList = videoService.findAllVideo(course.getId());
//
//				model.addAttribute("videoList", videoList);
//			}
//		}

//		List<Video> videoList = videoService.findAllVideo(id);
//
//		model.addAttribute("videoList", videoList);

		Subject subject = subjectService.findById(id);
		model.addAttribute("subject", subject);

//		Course course = subject.get(id);
//		model.addAttribute("course", course);

//		System.out.println("拿到了:" + subject);
		return "before/course.jsp";
	}

	@RequestMapping("selectByExample")
	public String selectByExample(Model model,
	                              SubjectExample example) {
		List<Subject> subjectList = subjectService.selectByExample(example);
		model.addAttribute("subjectList", subjectList);
//		model.addAttribute("subject", oneCourse);
		System.out.println(subjectList);
		return "/before/course.jsp";
	}
}
