import chess.dao.ChessGameDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameDAOTest {

    private ChessGameDAO chessGameDAO;
    @BeforeEach
    void setUp() {
        chessGameDAO = new ChessGameDAO();
    }

    @Test
    void testAddMoveCommand() throws SQLException {
        if (chessGameDAO.getRunningGameMove() != null) {
            testDeleteMoveCommand();
        }
        chessGameDAO.addMoveCommand("move b2 b4");
        chessGameDAO.addMoveCommand("move b4 b6");
        assertThat(chessGameDAO.getRunningGameMove()).isEqualTo(Arrays.asList("move b2 b4", "move b4 b6"));
    }

    @Test
    void testDeleteMoveCommand() throws SQLException {
        chessGameDAO.deleteMoveCommand("move b2 b4");
        chessGameDAO.deleteMoveCommand("move b4 b6");
    }

    @Test
    void testUpdateChessGameState() throws SQLException {
        if (chessGameDAO.getRunningGameMove() != null) {
            testDeleteMoveCommand();
        }
        chessGameDAO.addMoveCommand("move b2 b4");
        chessGameDAO.addMoveCommand("move b4 b6");
        chessGameDAO.updateChessGameState();
        assertThat(chessGameDAO.getRunningGameMove()).isEmpty();
    }
}
