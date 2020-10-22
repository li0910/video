package com.qf.controller;

//import com.github.pagehelper.PageInfo;

import com.qf.pojo.Subject;
import com.qf.pojo.SubjectExample;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("subject")
public class SubjectController {

//	subject/selectAll

	@Autowired
	private SubjectService subjectService;

	@RequestMapping("selectAll")
	public String findAllSubject(Model model, SubjectExample example
//	                             @RequestParam(defaultValue = "1", required = false) Integer pageNum,
//	                             @RequestParam(defaultValue = "2", required = false) Integer pageSize
	) {
//		PageHelper.startPage(pageNum, pageSize);

		List<Subject> findAllSubject = subjectService.selectByExample(example);

		model.addAttribute("subjectList", findAllSubject);

		return "before/index.jsp";

	}

}
