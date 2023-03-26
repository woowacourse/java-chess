package techcourse.fp.jdbc;

import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {
    private UserDao userDao;

    //테스트 메소드 실행 순서 정의 어떻게 할까?
    @BeforeEach
    void setUp(){
        userDao = new UserDao();
    }


    @Test
    void connection() {
        Connection connection = userDao.getConnection();

        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        User user = new User("hamad","hamadteco");
        userDao.insert(user);
    }

    @Test
    void findByUserId() {
        User hamad = userDao.findByUserId("hamad");
        assertThat(hamad).isEqualTo(new User("hamad","hamadteco"));
    }

    @Test
    void notFound() {
        User user = userDao.findByUserId("some");
        assertThat(user).isNull();
    }

    @Test
    void update() {
        User user = new User("hamad","hamadWooTeco");
        userDao.updateUser(user);
        assertThat(userDao.findByUserId("hamad")).isEqualTo(user);

    }

    @Test
    void delete() {
        User user = new User("hamad","hamadWooTeco");
        userDao.deleteUser(user);
        assertThat(userDao.findByUserId("hamad")).isNull();
    }
}
