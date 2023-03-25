package chess.domain.chess;

import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    @ParameterizedTest(name = "첫 턴은 백진영이 움직이는지 확인한다.")
    @CsvSource(value = {"6:6"}, delimiter = ':')
    void playMovableFail(final int rank, final int file) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(rank, file);
        final Position target = new Position(6, 7);

        // when, then
        assertThatThrownBy(() -> chessGame.run(source, target, CampType.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다. 현재 차례 = WHITE");
    }

    @Test
    @DisplayName("모든 진영의 왕이 살아있는 것이 아니라면 false를 반환한다.")
    void run() {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(1, 0);
        final Position target = new Position(3, 0);

        // when
        chessGame.run(source, target, CampType.WHITE);
        boolean isGameRun = ChessGameHelper.playKingDie(chessGame);

        // then
        assertThat(isGameRun)
                .isFalse();
    }

    @Test
    @DisplayName("WHITE 진영의 체스판을 반환한다.")
    void getWhiteBoard() {
        // given
        final ChessGame chessGame = new ChessGame();

        // when
        final Map<Position, Piece> whiteBoard = chessGame.getWhiteBoard();

        // then
        assertThat(whiteBoard.size())
                .isEqualTo(16);
    }

    @Test
    @DisplayName("BLACK 진영의 체스판을 반환한다.")
    void getBlackBoard() {
        // given
        final ChessGame chessGame = new ChessGame();

        // when
        final Map<Position, Piece> blackBoard = chessGame.getBlackBoard();

        // then
        assertThat(blackBoard.size())
                .isEqualTo(16);
    }
}
