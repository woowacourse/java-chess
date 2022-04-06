package web.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private PieceDao pieceDao;

    @BeforeEach
    void setUp() throws SQLException {
        pieceDao = new FakePieceDao();
        pieceDao.removeAll();
        pieceDao.initTurn();
    }

    @Test
    @DisplayName("기물을 정상적으로 가져오는지 확인")
    void load() throws SQLException {
        pieceDao.savePiece("D2", new Pawn(Color.WHITE, Position.from("d2")));

        assertAll(() -> {
            assertThat(pieceDao.load()).hasSize(1);
            assertThat(pieceDao.load()).contains(new Pawn(Color.WHITE, Position.from("d2")));
        });
    }

    @Test
    @DisplayName("차례를 정확하게 찾는지 확인")
    void findTurn() throws SQLException {
        assertThat(pieceDao.findTurn()).isEqualTo("WHITE");
    }

    @Test
    @DisplayName("기물들을 잘 가져오는지 확인")
    void findPieces() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(Color.WHITE, Position.from("d2")),
            new Rook(Color.BLACK, Position.from("d4"))));
        for (Piece piece : chessBoard.getPieces()) {
            pieceDao.savePiece(piece.getPosition().toString(), piece);
        }

        assertAll(() -> {
            assertThat(pieceDao.findPieces()).hasSize(2);
            assertThat(pieceDao.findPieces()).isEqualTo(chessBoard.getBoard());
        });
    }

    @Test
    @DisplayName("중복 위치 기물 저장할 경우 예외 발생")
    void throwExceptionSavePieceSamePosition() throws SQLException {
        pieceDao.savePiece("D2", new Pawn(Color.WHITE, Position.from("d2")));

        assertThatThrownBy(
            () -> pieceDao.savePiece("D2", new Pawn(Color.WHITE, Position.from("d2"))))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("기물의 위치는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("정상적으로 기물이 삭제되는지 확인")
    void deletePiece() throws SQLException {
        pieceDao.savePiece("D2", new Pawn(Color.WHITE, Position.from("d2")));

        assertAll(()-> {
            assertThat(pieceDao.load()).hasSize(1);
            pieceDao.deletePiece("D2");
            assertThat(pieceDao.load()).isEmpty();
        });
    }

    @Test
    @DisplayName("정상적으로 기물이 삭제되는지 확인")
    void throwExceptionDeletePieceNotFound() {
        assertThatThrownBy(() -> pieceDao.deletePiece("D2"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("차례가 정상적으로 업데이트 되는지 확인")
    void updateTurn() throws SQLException {
        pieceDao.updateTurn(Color.BLACK);

        assertThat(pieceDao.findTurn()).isEqualTo("BLACK");
    }
}
