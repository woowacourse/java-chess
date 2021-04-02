package chess.dao;

import chess.domain.ChessGame;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessDAOTest {
    private static ChessDAO chessDAO;

    @BeforeAll
    public static void setup() {
        chessDAO = new ChessDAO();
    }

    @Test
    public void connection() {
        Connection con = chessDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addUser() throws Exception {
        ChessGame game = new ChessGame(new BlackTeam(), new WhiteTeam());
        chessDAO.saveGame(game);
    }
}
