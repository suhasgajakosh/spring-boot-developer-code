package io.pivotal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.web.domain.Snippet;
import io.pivotal.web.repository.SnippetRepository;

@RestController
public class MainController {

	@Autowired
	SnippetRepository snippetRepository;
	
	@RequestMapping("/snippets")
	public List<Snippet> snippets(){
		assert snippetRepository != null;
		return snippetRepository.findAll();
	}
	
	@RequestMapping("/snippets/{id}")
	public Snippet snippet(@PathVariable("id") String id){
		assert snippetRepository != null;
		return snippetRepository.findById(id);
	}
	
}
