package chess.db;

import chess.domains.board.Board;
import chess.domains.piece.Pawn;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static chess.service.ChessWebService.BOARD_CELLS_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

class ChessPieceDaoTest {
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new ChessPieceDao();
    }

    @DisplayName("board_status 테이블에서 id에 해당하는 게임 히스토리 읽어오기 테스트")
    @Test
    void test() {
        Board board = new Board();      // 초기화된 board 생성
        List<ChessPiece> chessPieces = Position.stream()
                .map(position -> {
                    Piece piece = board.getPieceByPosition(position);
                    return new ChessPiece("test", position.name(), piece.name());
                })
                .collect(Collectors.toList());

        pieceDao.addInitialPieces(chessPieces);

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
        int rowCountBeforeInsert = pieceDao.countSavedPieces("test");
        assertThat(rowCountBeforeInsert).isEqualTo(0);

        ChessPiece aPiece = new ChessPiece("test", "c2", new Pawn(PieceColor.WHITE).name());
        pieceDao.addPiece(aPiece);

        int rowCountAfterInsert = pieceDao.countSavedPieces("test");
        assertThat(rowCountAfterInsert).isEqualTo(1);

        pieceDao.deleteBoardStatus("test");     // 테스트를 위해 추가한 데이터 삭제
    }

    @DisplayName("board_status 테이블에서 원하는 위치의 piece 값 읽기 테스트")
    @Test
    void name2() {
        ChessPiece aPiece = new ChessPiece("test", "c2", new Pawn(PieceColor.WHITE).name());
        pieceDao.addPiece(aPiece);

        String pieceName = pieceDao.findPieceNameByPosition("test", Position.ofPositionName("c2"));
        assertThat(pieceName).isEqualTo(aPiece.getPiece());

        pieceDao.deleteBoardStatus("test");     // 테스트를 위해 추가한 데이터 삭제
    }

    @DisplayName("board_status 테이블에서 원하는 위치의 piece 정보 업데이트 테스트")
    @Test
    void name3() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Rook rook = new Rook(PieceColor.WHITE);
        ChessPiece aPiece = new ChessPiece("test", "c2", new Pawn(PieceColor.WHITE).name());

        pieceDao.addPiece(aPiece);
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
        ChessPiece aPiece = new ChessPiece("test", "c2", new Pawn(PieceColor.WHITE).name());
        ChessPiece aPiece2 = new ChessPiece("test", "d2", new Pawn(PieceColor.WHITE).name());
        pieceDao.addPiece(aPiece);
        pieceDao.addPiece(aPiece2);

        int beforeDelete = pieceDao.countSavedPieces("test");
        assertThat(beforeDelete).isEqualTo(2);

        pieceDao.deleteBoardStatus("test");

        int afterDelete = pieceDao.countSavedPieces("test");
        assertThat(afterDelete).isEqualTo(0);
    }
}
