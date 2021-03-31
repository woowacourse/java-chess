package chess.domain.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDAOTest {
    private final User TEST_USER = new User("testUserId", "testUser");
    private UserDAO userDao;

    @BeforeEach
    public void setup() {
        userDao = new UserDAO();
    }

    @Test
    public void connection() {
        Connection con = userDao.getConnection();
        assertThat(con).isNotNull();
    }

//    @Test
//    public void addUser() throws Exception {
//        User user = new User("testUserId", "testUser");
//        userDao.addUser(user);
//    }

    @Test
    public void findByUserId() throws Exception {
        User user = userDao.findByUserId("testUserId");
        Assertions.assertEquals(TEST_USER, user);
    }

    @Test
    void close() {
        Connection con = userDao.getConnection();
        userDao.closeConnection(con);
    }
}

