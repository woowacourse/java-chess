package chess.service;

import chess.dao.chess.MockPieceDao;
import chess.dao.chess.PieceDao;
import chess.dao.chess.PieceEntityHelper;
import chess.domain.board.ChessBoard;
import chess.domain.chess.CampType;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.move.Position;
import chess.entity.PieceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardServiceTest {

    @Test
    @DisplayName("체스 게임 아이디로 체스판 정보를 조회한다.")
    void getByChessGameId() {
        // given
        final PieceDao pieceDao = createPieceDao();
        final ChessBoardService chessBoardService = new ChessBoardService(pieceDao);
        final ChessBoard expected = ChessBoardHelper.createMockProgressBoard();

        // when
        final ChessBoard actual = chessBoardService.getByChessGameId(1L);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("체스말 정보를 바탕으로 체스말을 저장한다.")
    void save() {
        // given
        final PieceDao pieceDao = new MockPieceDao();
        final ChessBoardService chessBoardService = new ChessBoardService(pieceDao);
        final ChessBoard expected = createMockChessBoard();

        // when
        chessBoardService.savePiece(PieceEntity.createWithChessGameId(1L, 1, 1,
                "PAWN", "WHITE"));

        // then
        final ChessBoard actual = chessBoardService.getByChessGameId(1L);
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("입력받은 위치에 해당하는 체스말을 제거한다")
    void deletePieces() {
        // given
        final PieceDao pieceDao = new MockPieceDao();
        final ChessBoardService chessBoardService = new ChessBoardService(pieceDao);
        final PieceEntity source = PieceEntity.createWithLocation(1L, 0, 0);
        final PieceEntity target = PieceEntity.createWithLocation(1L, 0, 1);
        chessBoardService.savePiece(source);
        chessBoardService.savePiece(target);

        // when
        chessBoardService.deletePieces(source, target);

        // then
        final ChessBoard actual = chessBoardService.getByChessGameId(1L);
        assertThat(actual)
                .isEqualTo(ChessBoard.create(Collections.emptyMap()));
    }

    private PieceDao createPieceDao() {
        final PieceDao pieceDao = new MockPieceDao();
        final List<PieceEntity> pieceEntities = PieceEntityHelper.createPieceEntities(1L);
        pieceEntities.forEach(pieceDao::save);
        return pieceDao;
    }

    private ChessBoard createMockChessBoard() {
        final Map<Position, Piece> mockBoard = new HashMap<>();
        mockBoard.put(new Position(1, 1), new Piece(PieceType.PAWN, CampType.WHITE, new Pawn()));
        return ChessBoard.create(mockBoard);
    }
}
