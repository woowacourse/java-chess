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

class KnightTest {

    @ParameterizedTest
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    @ValueSource(strings = {"c6", "d6", "e6", "e5", "e4", "d4", "c4", "c5", "d8", "a5"})
    void canMove_cantGo(final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final ChessPiece knight = Knight.from(Color.BLACK);

        // then
        assertThatThrownBy(() -> knight.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    @ValueSource(strings = {"c7", "c3", "b6", "b4", "f4", "f6", "e7", "e3"})
    void canMove_canGo(final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final ChessPiece knight = Knight.from(Color.BLACK);

        // then
        Assertions.assertThatCode(() -> knight.checkMovablePosition(from, to, null))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("b6");
        final ChessPiece knight = Knight.from(Color.BLACK);

        // when
        final Stack<Position> actual = knight.findRoute(from, to);

        // then
        assertThat(actual).isEmpty();
    }
}
