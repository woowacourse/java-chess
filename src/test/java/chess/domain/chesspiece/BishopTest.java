package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BishopTest {

    @ParameterizedTest
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    @ValueSource(strings = {"d6", "d4", "c5", "e5"})
    void canMove_cantGo(String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final ChessPiece bishop = Bishop.from(Color.BLACK);

        // then
        assertThatThrownBy(() -> bishop.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("이동 할 수 있는 위치에 같은 색 기물이 존재하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("b7");
        final ChessPiece bishop = Bishop.from(Color.BLACK);

        // then
        assertThatThrownBy(() -> bishop.checkMovablePosition(from, to, Bishop.from(Color.BLACK)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은색 기물입니다.");
    }

    @ParameterizedTest
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    @ValueSource(strings = {"b7", "f7", "f3", "b3"})
    void canMove_canGo(final String target) {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from(target);
        final ChessPiece bishop = Bishop.from(Color.BLACK);

        // then
        assertThatCode(() -> bishop.checkMovablePosition(from, to, null))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("h1");
        final ChessPiece bishop = Bishop.from(Color.BLACK);

        // when
        final Stack<Position> actual = bishop.findRoute(from, to);
        final List<Position> expected = List.of(Position.from("e4"), Position.from("f3"), Position.from("g2"));

        // then
        assertThat(actual).containsAll(expected);
    }
}
