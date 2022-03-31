package chess.domain.chesspiece;

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

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("c2");
        final ChessPiece queen = Queen.from(Color.BLACK);

        // then
        assertThatThrownBy(() -> queen.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    @ValueSource(strings = {"b7", "b5", "d7", "f7", "f5", "f3", "d3", "b3"})
    void canMove_canGo(final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final ChessPiece queen = Queen.from(Color.BLACK);

        // then
        Assertions.assertThatCode(() -> queen.checkMovablePosition(from, to, null))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_S() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("d1");
        final ChessPiece queen = Queen.from(Color.BLACK);

        // when
        final Stack<Position> actual = queen.findRoute(from, to);
        final List<Position> expected = List.of(Position.from("d4"), Position.from("d3"), Position.from("d2"));

        // then
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_NW() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("a8");
        final ChessPiece queen = Queen.from(Color.BLACK);

        // when
        final Stack<Position> actual = queen.findRoute(from, to);
        final List<Position> expected = List.of(Position.from("c6"), Position.from("b7"));

        // then
        assertThat(actual).containsAll(expected);
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute_SE() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("e4");
        final ChessPiece queen = Queen.from(Color.BLACK);

        // when
        final Stack<Position> actual = queen.findRoute(from, to);

        // then
        assertThat(actual).isEmpty();
    }
}
