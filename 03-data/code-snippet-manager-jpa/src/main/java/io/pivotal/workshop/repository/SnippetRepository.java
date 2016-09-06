package io.pivotal.workshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.workshop.domain.Snippet;

@Transactional
public interface SnippetRepository extends CrudRepository<Snippet, String> {

}
