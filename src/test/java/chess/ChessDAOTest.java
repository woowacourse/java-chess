package chess;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import chess.domain.User;

class ChessDAOTest {
    private ChessDAO chessDAO = new ChessDAO();

    @Test
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("testUser", "testPwd");
        chessDAO.addUser(user);
    }

    @Test
    void find() throws Exception{
        User user = chessDAO.findByUserId("2");
        assertEquals(new User("testUser", "testPwd"), user);
    }

    @Test
    void findNamePwd() throws Exception{
        User user = chessDAO.findByUserNameAndPwd("dusdn", "1230");
        assertEquals(new User("dusdn", "1230"), user);
    }
}