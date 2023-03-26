package chess.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {
    @Test
    void addPieceInBoardTable() {
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        assertDoesNotThrow(() -> chessBoardDao.addBoard(1, 2, 2, "p"));
    }

    @Test
    void findPieceInBoardTable() {
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        assertAll(
                () -> assertThat(chessBoardDao.findPiece(13, 1, 1)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPiece(13, 2, 1)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPiece(13, 3, 1)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPiece(13, 4, 1)).isEqualTo("QUEEN"),
                () -> assertThat(chessBoardDao.findPiece(13, 5, 1)).isEqualTo("KING"),
                () -> assertThat(chessBoardDao.findPiece(13, 6, 1)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPiece(13, 7, 1)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPiece(13, 8, 1)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPiece(13, 1, 2)).isEqualTo("PAWN"),

                () -> assertThat(chessBoardDao.findPiece(13, 1, 8)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPiece(13, 2, 8)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPiece(13, 3, 8)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPiece(13, 4, 8)).isEqualTo("QUEEN"),
                () -> assertThat(chessBoardDao.findPiece(13, 5, 8)).isEqualTo("KING"),
                () -> assertThat(chessBoardDao.findPiece(13, 6, 8)).isEqualTo("BISHOP"),
                () -> assertThat(chessBoardDao.findPiece(13, 7, 8)).isEqualTo("KNIGHT"),
                () -> assertThat(chessBoardDao.findPiece(13, 8, 1)).isEqualTo("ROOK"),
                () -> assertThat(chessBoardDao.findPiece(13, 1, 2)).isEqualTo("PAWN")
        );
    }

}
