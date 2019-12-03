package ru.ok.heisenbugdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ok.heisenbugdemo.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long> {}
