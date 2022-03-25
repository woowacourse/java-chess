package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KingTest {

    final Position initialPosition = new Position("d5");

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        final ChessPiece king = new King(Color.BLACK);

        // then
        assertThatThrownBy(() -> king.canMove(initialPosition, new Position("d7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    @ValueSource(strings = {"c4", "c5", "c6", "d4", "d6", "e4", "e5", "e6"})
    void canMove_canGo(final String target) {
        // given
        final ChessPiece king = new King(Color.BLACK);

        // then
        Assertions.assertThatCode(() -> king.canMove(initialPosition, new Position(target)))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute() {
        // given
        final ChessPiece king = new King(Color.BLACK);

        // when
        final Stack<Position> actual = king.findRoute(initialPosition, new Position("e4"));

        // then
        assertThat(actual).isEmpty();
    }
}
