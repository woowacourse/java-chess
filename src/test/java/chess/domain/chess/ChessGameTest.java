package chess.domain.chess;

import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    @ParameterizedTest(name = "첫 턴은 백진영의 폰과 나이트만 움직이는지 검증한다.")
    @CsvSource(value = {"6:0", "0:3"}, delimiter = ':')
    void playMovableFail(final int rank, final int file) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(rank, file);
        final Position target = new Position(5, 0);

        // when, then
        assertThatThrownBy(() -> chessGame.play(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다.");
    }

    @ParameterizedTest(name = "첫 턴의 폰은 2칸 이내만 전진할 수 있는지 검증한다.")
    @ValueSource(ints = {4, 5, 0})
    void playFirstTurnMovePawnFail(final int rank) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(1, 0);
        final Position target = new Position(rank, 0);

        // when, then
        assertThatThrownBy(() -> chessGame.play(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다.");
    }

    @ParameterizedTest(name = "첫 턴의 나이트는 L자로 전진할 수 있는지 검증한다.")
    @CsvSource(value = {"2:1", "3:2", "2:3"}, delimiter = ':')
    void playFirstTurnMoveKnightFail(final int rank, final int file) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(0, 1);
        final Position target = new Position(rank, file);

        // when, then
        assertThatThrownBy(() -> chessGame.play(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다.");
    }
}
