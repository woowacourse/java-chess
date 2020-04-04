package chess.dao;

import chess.domains.board.BoardFactory;
import chess.domains.piece.Pawn;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {

    @DisplayName("saved 테이블에서 id에 해당하는 게임 히스토리 읽어오기 테스트")
    @Test
    void test() throws SQLException {
        Map<Position, Piece> saved = BoardFactory.getBoard();
        PieceDao dao = new TestPieceDao("guest", saved);        // 초기화된 체스 피스 DB를 가지는 Dao 생성
        int savedCount = dao.countSavedInfo("guest");
        assertThat(savedCount).isEqualTo(64);
    }

    @DisplayName("saved 테이블에서 id에 해당하는 게임 히스토리 읽어오기 테스트")
    @Test
    void test2() throws SQLException {
        PieceDao dao = new TestPieceDao();          // 비어있는 DB를 가진 Dao 생성
        int savedCount = dao.countSavedInfo("guest");
        assertThat(savedCount).isEqualTo(0);
    }

    @DisplayName("saved 테이블에 row 추가 테스트")
    @Test
    void name() throws SQLException {
        PieceDao dao = new TestPieceDao();
        dao.addPiece("guest", Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));
    }

    @DisplayName("saved 테이블에서 원하는 위치의 piece 값 읽기 테스트")
    @Test
    void name2() throws SQLException {
        Map<Position, Piece> saved = new HashMap<>();
        saved.put(Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));
        PieceDao dao = new TestPieceDao("guest", saved);

        String pieceName = dao.findPieceNameByPosition("guest", Position.ofPositionName("c2"));
        assertThat(pieceName).isEqualTo("p");
    }

    @DisplayName("saved 테이블에서 원하는 위치의 piece 정보 업데이트 테스트")
    @Test
    void name3() throws SQLException {
        Map<Position, Piece> saved = new HashMap<>();
        saved.put(Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));
        PieceDao dao = new TestPieceDao("guest", saved);

        dao.updatePiece("guest", Position.ofPositionName("c2"), new Rook(PieceColor.WHITE));

        String pieceName = dao.findPieceNameByPosition("guest", Position.ofPositionName("c2"));
        assertThat(pieceName).isEqualTo("r");
    }

    @DisplayName("saved 테이블에서 row 삭제")
    @Test
    void name4() throws SQLException {
        Map<Position, Piece> saved = new HashMap<>();
        saved.put(Position.ofPositionName("a2"), new Pawn(PieceColor.WHITE));
        PieceDao dao = new TestPieceDao("guest", saved);

        dao.deletePiece("guest", Position.ofPositionName("a2"));

        String pieceName = dao.findPieceNameByPosition("guest", Position.ofPositionName("a2"));
        assertThat(pieceName).isNull();
    }

    @DisplayName("saved 테이블에서 saved 기록 전체 삭제")
    @Test
    void name5() throws SQLException {
        Map<Position, Piece> saved = new HashMap<>();
        saved.put(Position.ofPositionName("a2"), new Pawn(PieceColor.WHITE));
        PieceDao dao = new TestPieceDao("guest", saved);

        dao.deleteSaved("guest");

        int savedInfo = dao.countSavedInfo("guest");
        assertThat(savedInfo).isEqualTo(0);
    }
}
