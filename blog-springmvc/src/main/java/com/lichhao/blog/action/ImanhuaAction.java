package com.lichhao.blog.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImanhuaAction {

	@RequestMapping(value = "/imanhua")
	public String index() {
		return "imanhua/home";
	}

	@RequestMapping(value = "/imanhua/{commicName}")
	public ModelAndView show(@PathVariable("commicName") String commicName)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("imanhua/show");
		mv.addObject("commicName", commicName);
		return mv;
	}
}
