package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Speaker;
import com.qf.service.SpeakerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("/showSpeakerList")
    public String showSpeakerList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "8") Integer pageSize,
                                  Model model) {

        PageHelper.startPage(pageNum, pageSize);
        List<Speaker> speakerList = speakerService.findAll(null);
        PageInfo<Speaker> page = new PageInfo<Speaker>(speakerList);

        model.addAttribute("page",page);
        return "/behind/speakerList.jsp";
    }

    @RequestMapping("/addSpeaker")
    public String addSpeaker(){

        return "/behind/addSpeaker.jsp";
    }

    @RequestMapping("/edit")
    public String edit(Integer id,Model model) {
       Speaker speaker = speakerService.findById(id);

       model.addAttribute("speaker",speaker);
       return "/behind/addSpeaker.jsp";
    }

    @SneakyThrows
    @RequestMapping("/saveOrUpdate")
    public void saveOrUpdate(Speaker speaker, HttpServletResponse response) {

        if (speaker.getId() != null) {
            speakerService.updateSpeaker(speaker);
        } else {
            speakerService.insertSpeaker(speaker);
        }

        response.sendRedirect("/speaker/showSpeakerList");
    }

    @ResponseBody
    @RequestMapping("/speakerDel")
    public String speakerDel(Integer id) {
        speakerService.deleteSpeaker(id);

        return "success";
    }
}
