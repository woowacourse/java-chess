package chess.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.player.User;
import chess.util.RepositoryUtil;

class UserDAOTest {

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
    public void addUser() throws Exception {
        User user = new User("testUser");
        userDao.addUser(user);
    }

    @Test
    public void findByUserName() throws Exception {
        User user = userDao.findByUserName("testUser").orElse(null);
        assertEquals(new User("testUser"), user);
    }

    @Test
    public void updateNameByUserName() throws Exception {
        User user = userDao.updateUserNameByUserName("testUser", "changedName");
        assertEquals(new User("changedName"), user);
    }

    @Test
    public void deleteNameByUserName() throws Exception {
        Boolean isDeleted = userDao.deleteUserByUserName("testUser");
        assertEquals(isDeleted, true);
    }

    @Test
    public void repeatableCrud() throws Exception {
        connection();
        addUser();
        findByUserName();
        updateNameByUserName();
        deleteNameByUserName();
    }
}