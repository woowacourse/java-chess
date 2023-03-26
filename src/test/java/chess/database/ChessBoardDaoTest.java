package chess.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {
    @Test
    void addPieceInBoardTable() {
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        assertDoesNotThrow(() -> chessBoardDao.addBoard(1, 2, 2, "p"));
    }

}
