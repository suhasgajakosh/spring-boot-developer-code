package io.pivotal.workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.pivotal.workshop.domain.Snippet;
import io.pivotal.workshop.repository.SnippetRepository;

@RestController
public class SnippetController {

    @Autowired
    SnippetRepository snippetRepository;

    @GetMapping("/snippets")
    public List<Snippet> snippets() {
        assert snippetRepository != null;
        return snippetRepository.findAll();
    }

    @GetMapping("/snippets/{id}")
    public Snippet snippet(@PathVariable("id") String id) {
        assert snippetRepository != null;
        return snippetRepository.findById(id);
    }

    @PostMapping("/snippets")
    public ResponseEntity<?> add(@RequestBody Snippet snippet) {
        assert snippetRepository != null;

        Snippet _snippet = snippetRepository.save(snippet);
        assert _snippet != null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + _snippet.getId())
                .buildAndExpand().toUri());

        return new ResponseEntity<>(_snippet, httpHeaders, HttpStatus.CREATED);
    }

    //TODO: Challenge: Depending on the Accept header response a XML or JSON

    //TODO: Challenge: Add and PUT request method to update a give Snippet based on its ID.


}
