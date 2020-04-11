package chess.dao;

import chess.domains.board.BoardFactory;
import chess.domains.piece.Pawn;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.service.ChessWebService.BOARD_CELLS_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {

    @DisplayName("board_status 테이블에서 id에 해당하는 게임 히스토리 읽어오기 테스트")
    @Test
    void test() {
        Map<Position, Piece> boardStatus = BoardFactory.getBoard();
        PieceDao dao = new TestPieceDao("guest", boardStatus);        // 초기화된 체스 피스 DB를 가지는 Dao 생성
        int savedPiecesNumber = dao.countSavedPieces("guest");
        assertThat(savedPiecesNumber).isEqualTo(BOARD_CELLS_NUMBER);
    }

    @DisplayName("board_status 테이블에서 id에 해당하는 게임 히스토리 읽어오기 테스트")
    @Test
    void test2() {
        PieceDao dao = new TestPieceDao();          // 비어있는 DB를 가진 Dao 생성
        int savedPiecesNumber = dao.countSavedPieces("guest");
        assertThat(savedPiecesNumber).isEqualTo(0);
    }

    @DisplayName("board_status 테이블에 row 추가 테스트")
    @Test
    void name() {
        PieceDao dao = new TestPieceDao();
        dao.addPiece("guest", Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));
    }

    @DisplayName("board_status 테이블에서 원하는 위치의 piece 값 읽기 테스트")
    @Test
    void name2() {
        Map<Position, Piece> boardStatus = new HashMap<>();
        boardStatus.put(Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));
        PieceDao dao = new TestPieceDao("guest", boardStatus);

        String pieceName = dao.findPieceNameByPosition("guest", Position.ofPositionName("c2"));
        assertThat(pieceName).isEqualTo("p");
    }

    @DisplayName("board_status 테이블에서 원하는 위치의 piece 정보 업데이트 테스트")
    @Test
    void name3() {
        Map<Position, Piece> boardStatus = new HashMap<>();
        boardStatus.put(Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));
        PieceDao dao = new TestPieceDao("guest", boardStatus);

        dao.updatePiece("guest", Position.ofPositionName("c2"), new Rook(PieceColor.WHITE));

        String pieceName = dao.findPieceNameByPosition("guest", Position.ofPositionName("c2"));
        assertThat(pieceName).isEqualTo("r");
    }

    @DisplayName("board_status 테이블에서 board_status 기록 전체 삭제")
    @Test
    void name5() {
        Map<Position, Piece> boardStatus = new HashMap<>();
        boardStatus.put(Position.ofPositionName("a2"), new Pawn(PieceColor.WHITE));
        PieceDao dao = new TestPieceDao("guest", boardStatus);

        dao.deleteBoardStatus("guest");

        int savedInfo = dao.countSavedPieces("guest");
        assertThat(savedInfo).isEqualTo(0);
    }
}
