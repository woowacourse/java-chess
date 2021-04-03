package chess.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ChessRepositoryTest {
    @Test
    @DisplayName("dbUpdate 확인")
    public void updateDataBase() throws SQLException {
        ChessRepository chessRepository = new ChessRepository();
        chessRepository.updateBoard();
    }

}