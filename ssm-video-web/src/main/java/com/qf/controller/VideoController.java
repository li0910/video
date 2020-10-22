package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Course;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Speaker;
import com.qf.pojo.Video;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.VideoService;
import lombok.SneakyThrows;
import com.qf.pojo.Subject;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
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

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
	                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
	                         QueryVo queryVo) {
		ModelAndView modelAndView = new ModelAndView();

		// 查询所有的课程
		List<Course> courseList = courseService.findAll(null);


		// 查询所有的讲师
		List<Speaker> speakerList = speakerService.findAll(null);

		// 查询所有视频,并进行分页
		PageHelper.startPage(pageNum, pageSize);
		List<Video> videoList = videoService.findAll(queryVo);


		PageInfo<Video> page = new PageInfo<Video>(videoList);

		modelAndView.addObject("queryVo", queryVo);
		modelAndView.addObject("courseList", courseList);
		modelAndView.addObject("speakerList", speakerList);
		modelAndView.addObject("page", page);
		modelAndView.setViewName("/behind/videoList.jsp");
		return modelAndView;


	}

	@RequestMapping("/addVideo")
	public String addVideo(Model model) {
		// 查询所有的课程
		List<Course> courseList = courseService.findAll(null);

		// 查询所有的讲师
		List<Speaker> speakerList = speakerService.findAll(null);

		model.addAttribute("courseList", courseList);
		model.addAttribute("speakerList", speakerList);
		return "/behind/addVideo.jsp";
	}

	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		Video video = videoService.findById(id);

		// 查询所有的课程
		List<Course> courseList = courseService.findAll(null);

		// 查询所有的讲师
		List<Speaker> speakerList = speakerService.findAll(null);

		model.addAttribute("courseList", courseList);
		model.addAttribute("speakerList", speakerList);
		model.addAttribute("video", video);
		return "/behind/addVideo.jsp";
	}

	@SneakyThrows
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(Video video, HttpServletResponse response) {


		if (video.getId() != null) {
			videoService.updateVideo(video);
		} else {
			videoService.insertVideo(video);
		}

		response.sendRedirect("/video/list");
	}

	@RequestMapping("/videoDel")
	@ResponseBody
	public String videoDel(Integer id) {
		videoService.delete(id);
		return "success";
	}

	@SneakyThrows
	@RequestMapping("/delBatchVideos")
	public void delBatchVideos(Integer[] ids,HttpServletResponse response) {

		for (Integer id : ids) {
			videoService.delete(id);
		}
		response.sendRedirect("/video/list");
	}
}


