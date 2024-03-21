package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.position.Direction;
import chess.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KnightTest {

    @Test
    @DisplayName("나이트는 두 칸 전진한 뒤, 전진한 방향의 90도 좌/우 한 칸으로 이동할 수 있다")
    void knightMoveTest() {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        Assertions.assertAll(
                () -> assertThat(knight.isMovable(source, Position.of("f", 5))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of("b", 5))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of("f", 3))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of("b", 3))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of("e", 6))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of("c", 6))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of("e", 2))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of("c", 2))).isTrue()
        );
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class)
    @DisplayName("나이트가 이동할 수 없는 경우를 판단한다.")
    void knightInvalidMoveTest(Direction direction) {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of("d", 4);
        Position destination = direction.nextPosition(source);
        // when, then
        assertThat(knight.isMovable(source, destination)).isFalse();
    }
}
