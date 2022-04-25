package org.iii;

import lombok.extern.slf4j.Slf4j;
import org.iii.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Slf4j
@SpringBootTest
public class ApplicationTests {

    @Autowired
    BookRepository bookRepository;


    @Test
    @Transactional
    void jpaDefaultFindByMethodTest() {
        log.info(bookRepository.getOne(1).toString());
    }

}
