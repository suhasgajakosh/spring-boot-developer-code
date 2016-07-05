package io.pivotal.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.pivotal.web.domain.Snippet;

@Repository
public class SnippetRepository {
	
	@SuppressWarnings("serial")
	public List<Snippet> findAll(){
		return new ArrayList<Snippet>() {{
			add(new Snippet("JavaScript: Hello World","console.log('Hello World!');"));
			add(new Snippet("HTML: Hello World","<html><body><h1>Hello World</h1></body></html>"));
		}};
	}
	
}
