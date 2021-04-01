package chess;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import chess.dao.ChessDao;
import chess.dto.UserDto;
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
        UserDto userDto = new UserDto("testUser", "testPwd");
        chessDAO.addUser(userDto);
    }

    @Test
    void find() throws Exception {
        UserDto userDto = chessDAO.findByUserId("2");
        assertEquals(new UserDto("testUser", "testPwd"), userDto);
    }

    @Test
    void findNamePwd() throws Exception {
        UserDto userDto = chessDAO.findByUserNameAndPwd("dusdn", "1230");
        assertEquals(new UserDto("dusdn", "1230"), userDto);
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