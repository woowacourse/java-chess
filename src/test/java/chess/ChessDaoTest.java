package chess;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import chess.dao.ChessDao;
import chess.dao.SQLConnection;
import chess.domain.ChessGame;
import chess.domain.piece.Color;
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
    public void addUser() {
        UserDto userDto = new UserDto("testUser", "testPwd");
        assertDoesNotThrow(() -> chessDAO.addUser(userDto));
    }

    @Test
    void findUser() {
        UserDto userDto = chessDAO.findByUserId("13");
        assertEquals(new UserDto("testUser", "testPwd"), userDto);
    }

    @Test
    void findUserId() {
        String userId = chessDAO.findUserIdByUser(new UserDto("testUser", "testPwd"));
        assertEquals("13", userId);
    }

    @Test
    void findNamePwd() {
        UserDto userDto = chessDAO.findByUserNameAndPwd("dusdn", "1230");
        assertEquals(new UserDto("dusdn", "1230"), userDto);
    }

    @Test
    void addBoard() {
        assertDoesNotThrow(() -> chessDAO.addBoard("13", "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr", Color.WHITE));
    }

    @Test
    void findBoard() {
        BoardDto boardDto = chessDAO.findBoard("13");
        ChessGame chessGame = new ChessGame();
        assertEquals(boardDto.getBoard(), chessGame.getBoard());
    }

    @Test
    void deleteBoard() {
        assertDoesNotThrow(() -> chessDAO.deleteBoard("13"));
    }
}