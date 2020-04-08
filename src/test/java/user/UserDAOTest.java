package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDAOTest {
    private UserDAO userDao;

    @BeforeEach
    public void setup() {
        userDao = new UserDAO();
    }

    @Test
    public void connection() {
        Connection con = userDao.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() throws Exception {
        User user = userDao.findByUserId("testUserId");
        assertThat(UserTest.TEST_USER).isEqualTo(user);
    }

    @Test
    public void removeByUserId() throws Exception {
        userDao.removeByUserId("testUserId");
    }
}