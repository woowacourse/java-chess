package chess;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import chess.dao.ChessDao;
import chess.dto.User;
import chess.dto.BoardDto;

class ChessDaoTest {
    private final ChessDao chessDAO = new ChessDao();

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
    void find() throws Exception {
        User user = chessDAO.findByUserId("2");
        assertEquals(new User("testUser", "testPwd"), user);
    }

    @Test
    void findNamePwd() throws Exception {
        User user = chessDAO.findByUserNameAndPwd("dusdn", "1230");
        assertEquals(new User("dusdn", "1230"), user);
    }

    @Test
    void findBoard() throws Exception {
        // Map<Point, Piece> map = new HashMap<>();
        // map.put(Point.of("a8"), new Rook())
        BoardDto boardDto = chessDAO.findBoard("1");
        // assertEquals(boardDto, )
        System.out.println(boardDto.getBoard());
    }
}