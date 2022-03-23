package chess.domain.chessPiece;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    Position initialPosition = new Position("d5");

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        ChessPiece king = new King(Color.BLACK);

        // when
        // then
        assertThatThrownBy(() -> king.canMove(initialPosition, new Position("d7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"c4", "c5", "c6", "d4", "d6", "e4", "e5", "e6"})
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    void canMove_canGo(String target) {
        // given
        ChessPiece king = new King(Color.BLACK);

        // when
        // then
        Assertions.assertThatCode(() -> king.canMove(initialPosition, new Position(target)))
                .doesNotThrowAnyException();

    }
}