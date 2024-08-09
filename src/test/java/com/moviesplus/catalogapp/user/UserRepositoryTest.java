package com.moviesplus.catalogapp.user;

import com.moviesplus.catalogapp.MoviesPlusApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        User user = new User();
        user.setName("John Doe");

        entityManager.persist(user);
        entityManager.flush();

        List<User> users = userRepository.findAll();

        assertThat(users).isNotNull();
        assertThat(users.get(0).getName()).isEqualTo("John Doe");
    }

}
