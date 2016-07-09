package io.pivotal.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
	
	
	@RequestMapping("/")
	public ModelAndView home(){
		return new ModelAndView("views/home");
	}
	
	/*
	@Autowired
	SnippetRepository snippetRepository;
	
	@RequestMapping("/snippets")
	public List<Snippet> snippets(){
		assert snippetRepository != null;
		return snippetRepository.findAll();
	}
	
	@RequestMapping("/snippets/{id}")
	public Snippet snippet(@PathVariable("id") String id){
		return snippetRepository.findById(id);
	}
	*/
}
