package com.example.tododb.repositories;

import com.example.tododb.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select todo from Todo todo where todo.id = ?1")
    Todo findOne(Long id);
}
