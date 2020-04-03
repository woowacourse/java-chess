package chess.dao;

import chess.domains.piece.Pawn;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {

    @DisplayName("game_history 테이블에 row 추가 테스트")
    @Test
    void name() throws SQLException {
        PieceDao pieceDao = new PieceDao();
        pieceDao.addPiece("guest", "c2", new Pawn(PieceColor.WHITE));
    }

    @DisplayName("game_history 테이블에서 row (piece) 읽기 테스트")
    @Test
    void name2() throws SQLException {
        PieceDao pieceDao = new PieceDao();
        Piece piece = pieceDao.findPieceByPosition("guest", "c2");
        assertThat(piece).isEqualTo(new Pawn(PieceColor.WHITE));
    }

    @DisplayName("game_history 테이블에서 row 업데이트 테스트")
    @Test
    void name3() throws SQLException {
        PieceDao dao = new PieceDao();
        dao.updatePiece("guest", "a2", new Rook(PieceColor.WHITE));

        Piece actual = dao.findPieceByPosition("guest", "a2");
        assertThat(actual).isEqualTo(new Rook(PieceColor.WHITE));
    }

    @DisplayName("game_history 테이블에서 row 삭제")
    @Test
    void name4() throws SQLException {
        PieceDao dao = new PieceDao();
        dao.deletePiece("guest", "a2");

        Piece actual = dao.findPieceByPosition("guest", "a2");
        assertThat(actual).isNull();
    }
}
