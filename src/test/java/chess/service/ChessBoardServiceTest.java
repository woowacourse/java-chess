package chess.service;

import chess.dao.chess.MockPieceDao;
import chess.dao.chess.PieceDao;
import chess.dao.chess.PieceEntityHelper;
import chess.domain.board.ChessBoard;
import chess.entity.PieceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardServiceTest {

    @Test
    @DisplayName("체스 게임 아이디로 체스판 정보를 조회한다.")
    void getByChessGameId() {
        // given
        final PieceDao pieceDao = new MockPieceDao();
        final List<PieceEntity> pieceEntities = PieceEntityHelper.createPieceEntities(1L);
        pieceEntities.forEach(pieceDao::save);
        final ChessBoardService chessBoardService = new ChessBoardService(pieceDao);
        final ChessBoard expected = ChessBoardHelper.createMockProgressBoard();

        // when
        final ChessBoard actual = chessBoardService.getByChessGameId(1L);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
