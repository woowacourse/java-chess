package web.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import chess.domain.position.ChessBoardPosition;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new FakePieceDao();
    }

    @Test
    @DisplayName("Piece들이 정확하게 저장 되는지 확인")
    void savePiece() throws SQLException {
        pieceDao.savePiece("E6", new Rook(Color.WHITE, ChessBoardPosition.from("E6")), 1L);

        Assertions.assertThat(pieceDao.findPieces(1L)).hasSize(1);
    }

    @Test
    @DisplayName("기존에 저장되어 있는 다른 Piece들이 있는 경우")
    void savePieceBeforeSaveSomePiece() throws SQLException {
        pieceDao.savePiece("E6", new Rook(Color.WHITE, ChessBoardPosition.from("E6")), 1L);
        pieceDao.savePiece("E7", new Rook(Color.WHITE, ChessBoardPosition.from("E7")), 1L);

        Assertions.assertThat(pieceDao.findPieces(1L)).hasSize(2);
    }

    @Test
    @DisplayName("piece가 정상적으로 삭제되는지 확인")
    void deletePiece() throws SQLException {
        pieceDao.savePiece("E2", new Pawn(Color.WHITE, ChessBoardPosition.from("E2")), 1L);
        pieceDao.deletePiece("E2", 1L);

        Assertions.assertThat(pieceDao.findPieces(1L)).isEmpty();
    }

}
