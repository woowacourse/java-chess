package chess.service;

import chess.dao.chess.MockPieceDao;
import chess.domain.board.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardServiceTest {

    @Test
    @DisplayName("체스 게임 아이디로 체스판 정보를 조회한다.")
    void getByChessGameId() {
        // given
        final ChessBoardService chessBoardService = new ChessBoardService(new MockPieceDao());
        final ChessBoard expected = ChessBoardHelper.createMockBoard();

        // when
        final ChessBoard actual = chessBoardService.getByChessGameId(1L);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
