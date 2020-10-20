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
import com.qf.videos.utils.Page;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private CourseService courseService;

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
