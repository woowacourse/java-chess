package chess;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import chess.dao.ChessDao;
import chess.dao.SQLConnection;
import chess.dto.UserDto;
import chess.dto.BoardDto;

class ChessDaoTest {
    private final SQLConnection connection = new SQLConnection();
    private final ChessDao chessDAO = new ChessDao(connection);

    @Test
    public void connection() {
        Connection con = connection.getConnection();
        assertNotNull(con);
    }

    @Disabled
    @Test
    public void addUser() throws Exception {
        UserDto userDto = new UserDto("testUser", "testPwd");
        chessDAO.addUser(userDto);
    }

    @Test
    void find() throws Exception {
        UserDto userDto = chessDAO.findByUserId("13");
        assertEquals(new UserDto("testUser", "testPwd"), userDto);
    }

    @Test
    void findNamePwd() throws Exception {
        UserDto userDto = chessDAO.findByUserNameAndPwd("dusdn", "1230");
        assertEquals(new UserDto("dusdn", "1230"), userDto);
    }

    @Test
    void findBoard() throws Exception {
        BoardDto boardDto = chessDAO.findBoard("1");
        System.out.println(boardDto.getBoard());
    }
}