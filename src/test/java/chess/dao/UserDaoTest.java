package chess.dao;

import chess.domain.User;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    @Test
    public void connection() {
        UserDao userDAO = new UserDao();
        Connection connection = userDAO.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("testUserId", "testUser");
        UserDao userDAO = new UserDao();
        userDAO.addUser(user);
    }

    @Test
    public void findByUserId() throws Exception {
        UserDao userDAO = new UserDao();
        User user = userDAO.findByUserId("testUserId");
        assertThat(user.getName()).isEqualTo("testUser");
    }
}
