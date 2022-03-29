package chess.domain.piece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    Position initialPosition = new Position("d5");
    ChessPiece queen = new Queen(Color.BLACK);

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // then
        assertThatThrownBy(() -> queen.canMove(initialPosition, new Position("c2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "b5", "d7", "f7", "f5", "f3", "d3", "b3"})
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    void canMove_canGo(String target) {
        // then
        Assertions.assertThatCode(() -> queen.canMove(initialPosition, new Position(target)))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_S() {
        // when
        Stack<Position> actual = queen.findRoute(initialPosition, new Position("d1"));
        List<Position> expected = List.of(new Position("d4"), new Position("d3"), new Position("d2"));
        // then
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_NW() {
        // when
        Stack<Position> actual = queen.findRoute(initialPosition, new Position("a8"));
        List<Position> expected = List.of(new Position("c6"), new Position("b7"));
        // then
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_SE() {
        // when
        Stack<Position> actual = queen.findRoute(initialPosition, new Position("e4"));
        // then
        assertThat(actual).isEmpty();
    }
}
