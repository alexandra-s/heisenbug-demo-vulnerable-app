package ru.ok.heisenbugdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ok.heisenbugdemo.model.Comment;
import ru.ok.heisenbugdemo.model.Photo;

public interface CommentRepository extends CrudRepository<Comment, Long> {}

