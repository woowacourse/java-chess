package chess.domain.chess;

import chess.domain.piece.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        assertThatThrownBy(() -> chessGame.setUp(source, target, CampType.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다. 현재 차례 = WHITE");
    }

    @Test
    @DisplayName("현재 체스 게임에서 킹이 살아있는지 판단한다.")
    void isKingAlive() {
        // given
        final ChessGame chessGame = new ChessGame();

        // when, then
        assertThat(chessGame.isKingAlive())
                .isTrue();
    }
}
