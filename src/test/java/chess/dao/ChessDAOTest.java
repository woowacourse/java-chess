package chess.dao;

import chess.repository.MySqlChessRepository;
import chess.service.ChessGameService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessDAOTest {
    private static ChessDAO chessDAO;
    private static ChessGameService chessGameService;
    @BeforeAll
    public static void setup() {
        chessDAO = new ChessDAO();
        chessGameService = new ChessGameService(new MySqlChessRepository());
    }

    @Test
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addChessGame() throws Exception {
        chessGameService.createChessGame();
    }

    @Test
    public void addRoom() throws Exception {
        chessGameService.createRoom("{name: 수리, pw: 1234}");
    }
}
