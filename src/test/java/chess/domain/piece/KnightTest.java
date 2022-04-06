package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    private Position initialPosition = new Position("d5");

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // given
        ChessPiece knight = new Knight(Color.BLACK);
        // then
        assertThatThrownBy(() -> knight.checkMovable(initialPosition, new Position("d7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"c7", "c3", "b6", "b4", "f4", "f6", "e7", "e3"})
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    void canMove_canGo(String target) {
        // given
        ChessPiece knight = new Knight(Color.BLACK);
        // then
        Assertions.assertThatCode(() -> knight.checkMovable(initialPosition, new Position(target)))
                .doesNotThrowAnyException();

    }

}
