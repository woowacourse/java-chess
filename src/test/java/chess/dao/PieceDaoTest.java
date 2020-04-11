package chess.dao;

import chess.domains.board.Board;
import chess.domains.piece.Pawn;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.service.ChessWebService.BOARD_CELLS_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new ChessPieceDao();
    }

    @DisplayName("board_status 테이블에서 id에 해당하는 게임 히스토리 읽어오기 테스트")
    @Test
    void test() {
        Board board = new Board();      // 초기화된 board 생성
        Position.stream()               // 초기화된 board 를 DB에 insert
                .forEach(position -> pieceDao.addPiece("test", position, board.getPieceByPosition(position)));

        int savedPiecesNumber = pieceDao.countSavedPieces("test");

        assertThat(savedPiecesNumber).isEqualTo(BOARD_CELLS_NUMBER);

        pieceDao.deleteBoardStatus("test");     // 테스트를 위해 추가한 데이터 삭제
    }

    @DisplayName("board_status 테이블에서 id에 해당하는 게임 히스토리 읽어오기 테스트")
    @Test
    void test2() {
        int savedPiecesNumber = pieceDao.countSavedPieces("test");

        assertThat(savedPiecesNumber).isEqualTo(0);
    }

    @DisplayName("board_status 테이블에 한 row 추가 테스트")
    @Test
    void name() {
        pieceDao.addPiece("test", Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));

        int rowCount = pieceDao.countSavedPieces("test");
        assertThat(rowCount).isEqualTo(1);

        pieceDao.deleteBoardStatus("test");     // 테스트를 위해 추가한 데이터 삭제
    }

    @DisplayName("board_status 테이블에서 원하는 위치의 piece 값 읽기 테스트")
    @Test
    void name2() {
        Pawn pawn = new Pawn(PieceColor.WHITE);

        pieceDao.addPiece("test", Position.ofPositionName("c2"), pawn);

        String pieceName = pieceDao.findPieceNameByPosition("test", Position.ofPositionName("c2"));
        assertThat(pieceName).isEqualTo(pawn.name());

        pieceDao.deleteBoardStatus("test");     // 테스트를 위해 추가한 데이터 삭제
    }

    @DisplayName("board_status 테이블에서 원하는 위치의 piece 정보 업데이트 테스트")
    @Test
    void name3() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Rook rook = new Rook(PieceColor.WHITE);

        pieceDao.addPiece("test", Position.ofPositionName("c2"), pawn);
        String beforeUpdate = pieceDao.findPieceNameByPosition("test", Position.ofPositionName("c2"));
        assertThat(beforeUpdate).isEqualTo(pawn.name());

        pieceDao.updatePiece("test", Position.ofPositionName("c2"), rook);

        String pieceName = pieceDao.findPieceNameByPosition("test", Position.ofPositionName("c2"));
        assertThat(pieceName).isNotEqualTo(pawn.name());
        assertThat(pieceName).isEqualTo(rook.name());

        pieceDao.deleteBoardStatus("test");     // 테스트를 위해 추가한 데이터 삭제
    }

    @DisplayName("board_status 테이블에서 board_status 기록 전체 삭제")
    @Test
    void name5() {
        pieceDao.addPiece("test", Position.ofPositionName("c2"), new Pawn(PieceColor.WHITE));
        pieceDao.addPiece("test", Position.ofPositionName("d2"), new Pawn(PieceColor.WHITE));

        int beforeDelete = pieceDao.countSavedPieces("test");
        assertThat(beforeDelete).isEqualTo(2);

        pieceDao.deleteBoardStatus("test");

        int afterDelete = pieceDao.countSavedPieces("test");
        assertThat(afterDelete).isEqualTo(0);
    }
}
