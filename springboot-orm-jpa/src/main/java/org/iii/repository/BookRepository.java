package org.iii.repository;

import org.iii.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

    @Query(value = "SELECT * FROM book", nativeQuery = true)
    List<Book> selectWithNativeQuery();

}
