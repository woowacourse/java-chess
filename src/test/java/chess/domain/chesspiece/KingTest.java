package chess.domain.chesspiece;

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

    final Position initialPosition = Position.from("d5");

    @ParameterizedTest
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    @ValueSource(strings = {"d7", "b5", "d3", "f5"})
    void canMove_cantGo(final String target) {
        // given
        final ChessPiece king = King.from(Color.BLACK);

        // then
        assertThatThrownBy(() -> king.canMove(initialPosition, Position.from(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    @ValueSource(strings = {"c4", "c5", "c6", "d4", "d6", "e4", "e5", "e6"})
    void canMove_canGo(final String target) {
        // given
        final ChessPiece king = King.from(Color.BLACK);

        // then
        Assertions.assertThatCode(() -> king.canMove(initialPosition, Position.from(target)))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute() {
        // given
        final ChessPiece king = King.from(Color.BLACK);

        // when
        final Stack<Position> actual = king.findRoute(initialPosition, Position.from("e4"));

        // then
        assertThat(actual).isEmpty();
    }
}
