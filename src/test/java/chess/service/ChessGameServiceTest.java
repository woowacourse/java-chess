package chess.service;

import chess.dao.chess.MockChessGameDao;
import chess.dao.chess.MockPieceDao;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameServiceTest {

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하지 않으면 새 게임을 반환한다.")
    void getChessGame_empty() {
        // given
        final ChessGameService chessGameService = new ChessGameService(
                new MockChessGameDao(), new ChessBoardService(new MockPieceDao()));
        final ChessGame expected = new ChessGame(ChessBoardHelper.createMockBoard(), null);

        // when
        final ChessGame actual = chessGameService.getChessGame(2L);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하면 해당 게임을 반환한다.")
    void getChessGame() {
        // given
        final ChessGameService chessGameService = new ChessGameService(
                new MockChessGameDao(), new ChessBoardService(new MockPieceDao()));
        final ChessGame expected = new ChessGame(ChessBoardHelper.createMockProgressBoard(), CampType.WHITE);

        // when
        final ChessGame actual = chessGameService.getChessGame(1L);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
