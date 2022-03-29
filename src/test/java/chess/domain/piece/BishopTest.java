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

class BishopTest {

    Position initialPosition = new Position("d5");

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        ChessPiece bishop = new Bishop(Color.BLACK);
        // then
        assertThatThrownBy(() -> bishop.canMove(initialPosition, new Position("d6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "f7", "f3", "b3"})
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    void canMove_canGo() {
        // given
        ChessPiece bishop = new Bishop(Color.BLACK);
        // then
        Assertions.assertThatCode(() -> bishop.canMove(initialPosition, new Position("c4")))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute() {
        // given
        ChessPiece bishop = new Bishop(Color.BLACK);
        // when
        Stack<Position> actual = bishop.findRoute(initialPosition, new Position("h1"));
        List<Position> expected = List.of(new Position("e4"), new Position("f3"), new Position("g2"));
        // then
        assertThat(actual).containsAll(expected);
    }
}
