package com.qf.controller;

import com.qf.pojo.Course;
import com.qf.pojo.Subject;
import com.qf.pojo.Video;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.SubjectService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {

	@Autowired
	private SpeakerService speakerService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private VideoService videoService;

	@RequestMapping("showVideo")
	public String findVideo(int videoId, String subjectName, Model model) {
//      导航栏信息
		List<Subject> subjectList = subjectService.selectByExample(null);
		model.addAttribute("subjectList", subjectList);
//		传入课程名
		model.addAttribute("subjectName", subjectName);
		System.out.println(videoId + ":" + subjectName);

		Video video = videoService.findByVideoId(videoId);
		model.addAttribute("video", video);
		System.out.println("对应视频" + video);
//      传入当前课程介绍
		Integer courseId = video.getCourseId();
		Course course1 = courseService.findOneCourse(courseId);
		model.addAttribute("course1", course1);
		System.out.println("对应课程" + course1);

//		-----------------------------
		List<Video> videoList = videoService.findAllVideo(courseId);
		model.addAttribute("videoList", videoList);

//		-----------------------------
//		通过学科名获取课程名
//		Subject byName = subjectService.findByName(subjectName);
//		Integer id = byName.getId();
//		List<Course> courseList = courseService.findAllCourse(id);
//
//		for (Course course : courseList) {
//			List<Video> videoList = videoService.findAllVideo(course.getId());
//
//			model.addAttribute("videoList", videoList);
//			System.out.println("----------" + videoList);
//		}


//		for (Course course : courseList) {
//
//			if (course1.getCourseTitle().equals(course.getCourseTitle())) {
//				List<Video> videoList = videoService.findAllVideo(course.getId());
//
//				model.addAttribute("videoList", videoList);
//				System.out.println("----------" + videoList);
//			}
//
//		}


		return "before/section.jsp";
	}

}
