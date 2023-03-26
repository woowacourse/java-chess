package chess.domain.game;

import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    @ParameterizedTest(name = "첫 턴은 백진영의 폰과 나이트만 움직이는지 검증한다.")
    @CsvSource(value = {"1:0", "0:1"}, delimiter = ':')
    void playMovableFail(final int rank, final int file) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = Position.of(rank, file);
        final Position target = Position.of(2, 0);

        // when, then
        assertThatNoException().isThrownBy(() -> chessGame.play(source, target));
    }

    @ParameterizedTest(name = "첫 턴의 폰은 2칸 이내만 전진할 수 있는지 검증한다.")
    @ValueSource(ints = {4, 5, 0})
    void playFirstTurnMovePawnFail(final int rank) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = Position.of(1, 0);
        final Position target = Position.of(rank, 0);

        // when, then
        assertThatThrownBy(() -> chessGame.play(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물 규칙 상 움직일 수 없는 위치입니다.");
    }

    @ParameterizedTest(name = "첫 턴의 나이트는 L자로 전진할 수 있는지 검증한다.")
    @CsvSource(value = {"2:1", "3:2", "2:3"}, delimiter = ':')
    void playFirstTurnMoveKnightFail(final int rank, final int file) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = Position.of(0, 1);
        final Position target = Position.of(rank, file);

        // when, then
        assertThatThrownBy(() -> chessGame.play(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물 규칙 상 움직일 수 없는 위치입니다.");
    }
}
