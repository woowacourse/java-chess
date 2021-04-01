package chess.dao;

import chess.domain.game.ChessGameEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDAOTest {

    private ChessGameDAO chessGameDAO;

    @BeforeEach
    void setUp() {
        chessGameDAO = new ChessGameDAO();
    }

    @Test
    void testCRD() throws SQLException {
        //given //when
        chessGameDAO.create();

        //then
        ChessGameEntity lastChessGame = chessGameDAO.findLatestOne().get();
        assertThat(lastChessGame).isNotNull();
        chessGameDAO.deleteById(lastChessGame.getId());
    }

}
