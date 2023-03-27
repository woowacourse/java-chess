package techcourse.fp.mission;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {
    final UserDao userDao = new UserDao();

    @Order(1)
    @Test
    public void connection() {
        final Connection connection = userDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Order(2)
    @Test
    void save() {
        final User user = new User("neo", "neo");
        userDao.insert(user);
    }

    @Order(3)
    @Test
    void findById() {
        final User neo = userDao.findByUserId("neo");
        assertThat(neo).isEqualTo(new User("neo", "neo"));
    }

    @Order(4)
    @Test
    void notFound() {
        final User user = userDao.findByUserId("hi");
        assertThat(user).isNull();
    }

    @Order(5)
    @Test
    void updateTest() {
        final User changedNeo = userDao.findByUserId("neo");
        userDao.update(changedNeo);
        final User updated = userDao.findByUserId("neo");
        assertThat(updated.name()).isEqualTo("changedName");
    }

    @Order(6)
    @Test
    void deleteTest() {
        final User deleteNeo = userDao.findByUserId("neo");
        userDao.delete(deleteNeo);
        assertThat(deleteNeo).isNotNull();
    }
}
