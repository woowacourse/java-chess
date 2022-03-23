package chess.domain.chessPiece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    Position initialPosition = new Position("d5");

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        ChessPiece bishop = new Bishop(Color.BLACK);

        // when
        // then
        assertThatThrownBy(() -> bishop.canMove(initialPosition, new Position("d6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    void canMove_canGo() {
        // given
        ChessPiece bishop = new Bishop(Color.BLACK);

        // when
        // then
        Assertions.assertThatCode(() -> bishop.canMove(initialPosition, new Position("c4")))
                .doesNotThrowAnyException();

    }
}