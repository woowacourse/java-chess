package chess.domain.player;

import chess.repository.RepositoryUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDAOTest {

    public static final String userName1 = "testUser1";
    public static final String userName2 = "testUser2";

    private UserDAO userDao;

    @BeforeEach
    public void setup() {
        userDao = new UserDAO();
    }

    @Test
    public void connection() {
        Connection con = RepositoryUtil.getConnection();
        assertNotNull(con);
    }

    @Test
    public void crud() throws Exception {
        userDao.deleteUserByUserName("changedName");
        userDao.addUser(new User("testUser"));
        userDao.updateUserNameByUserName("testUser", "changedName");
        User user = userDao.findByUserName("changedName");
        assertEquals(user, new User("changedName"));
    }

    public static void addTwoTestUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        User user1 = new User(UserDAOTest.userName1);
        User user2 = new User(UserDAOTest.userName2);
        userDAO.addUser(user1);
        userDAO.addUser(user2);
    }

    public static void deleteTwoTestUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUserByUserName(UserDAOTest.userName1);
        userDAO.deleteUserByUserName(UserDAOTest.userName2);
    }
}



