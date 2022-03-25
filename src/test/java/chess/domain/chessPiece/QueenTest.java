package chess.domain.chessPiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import java.util.List;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QueenTest {

    final Position initialPosition = Position.from("d5");
    final ChessPiece queen = new Queen(Color.BLACK);

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // then
        assertThatThrownBy(() -> queen.canMove(initialPosition, Position.from("c2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    @ValueSource(strings = {"b7", "b5", "d7", "f7", "f5", "f3", "d3", "b3"})
    void canMove_canGo(final String target) {
        // then
        Assertions.assertThatCode(() -> queen.canMove(initialPosition, Position.from(target)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_S() {
        // when
        final Stack<Position> actual = queen.findRoute(initialPosition, Position.from("d1"));
        final List<Position> expected = List.of(Position.from("d4"), Position.from("d3"), Position.from("d2"));

        // then
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_NW() {
        // when
        final Stack<Position> actual = queen.findRoute(initialPosition, Position.from("a8"));
        final List<Position> expected = List.of(Position.from("c6"), Position.from("b7"));

        // then
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_SE() {
        // when
        final Stack<Position> actual = queen.findRoute(initialPosition, Position.from("e4"));

        // then
        assertThat(actual).isEmpty();
    }
}
