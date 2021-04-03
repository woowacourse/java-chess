package chess;

import chess.dao.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDAOTest {
    private UserDao userDao;

    @BeforeEach
    public void setup() {
        userDao = new UserDao();
    }

    @Test
    public void connection() {
        Connection con = userDao.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("testUserId");
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() throws Exception {
        User user = userDao.findByUserId("testUserId");
        assertEquals("testUserId", user.getUserId());
    }
}
