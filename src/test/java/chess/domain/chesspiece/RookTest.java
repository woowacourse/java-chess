package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import java.util.List;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("c6");
        final ChessPiece rook = Rook.from(Color.BLACK);

        // then
        assertThatThrownBy(() -> rook.checkMovablePosition(from, to, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    void canMove_canGo() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("c5");
        final ChessPiece rook = Rook.from(Color.BLACK);

        // then
        Assertions.assertThatCode(() -> rook.checkMovablePosition(from, to, null))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("목적지까지 경로를 구한다.")
    void findRoute() {
        // given
        final Position from = Position.from("d5");
        final Position to = Position.from("d1");
        final ChessPiece rook = Rook.from(Color.BLACK);

        // when
        final Stack<Position> actual = rook.findRoute(from, to);
        final List<Position> expected = List.of(Position.from("d4"), Position.from("d3"), Position.from("d2"));

        // then
        assertThat(actual).containsAll(expected);
    }
}
