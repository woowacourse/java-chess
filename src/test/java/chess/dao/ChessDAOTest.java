package chess.dao;

import chess.domain.ChessGame;
import chess.dto.ChessDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessDAOTest {
    private ChessGame chessGame;
    private ChessDAO chessDAO;

    @BeforeEach
    public void setUp() {
        chessGame = new ChessGame();
        chessDAO = ChessDAO.getInstance();
    }

    @Test
    public void addTest() throws SQLException {
        ChessDTO chessDTO = chessGame.toDTO();
        chessDAO.addChessGame(chessDTO);
    }

    @Test
    public void findChessGameTest() throws SQLException {
        ChessGame chessGame = new ChessGame();
        assertThat(chessDAO.findChessGame()).isEqualTo(chessGame);
    }
}