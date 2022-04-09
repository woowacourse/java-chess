package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    private Position initialPosition = new Position("d5");
    private ChessPiece queen = new Queen(Color.BLACK);

    @Test
    @DisplayName("이동 할 수 없는 위치로 이동하면 예외를 던진다.")
    void canMove_cantGo() {
        // then
        assertThatThrownBy(() -> queen.checkMovable(initialPosition, new Position("c2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물이 갈 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "b5", "d7", "f7", "f5", "f3", "d3", "b3"})
    @DisplayName("이동 할 수 있는 위치라면 예외를 던지지 않는다.")
    void canMove_canGo(String target) {
        // then
        Assertions.assertThatCode(() -> queen.checkMovable(initialPosition, new Position(target)))
                .doesNotThrowAnyException();

    }
}
