package chess;

import chess.dao.UserDao;
import chess.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDtoDAOTest {
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
        UserDto userDto = new UserDto("testUserId");
        userDao.addUser(userDto);
    }

    @Test
    public void findByUserId() throws Exception {
        UserDto userDto = userDao.findByUserId("testUserId");
        assertEquals("testUserId", userDto.getUserId());
    }
}
