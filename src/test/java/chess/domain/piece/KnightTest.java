package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
        Position source = Position.of(File.D, Rank.FOUR);
        // when, then
        Assertions.assertAll(
                () -> assertThat(knight.isMovable(source, Position.of(File.F, Rank.FIVE))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of(File.B, Rank.FIVE))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of(File.F, Rank.THREE))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of(File.B, Rank.THREE))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of(File.E, Rank.SIX))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of(File.C, Rank.SIX))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of(File.E, Rank.TWO))).isTrue(),
                () -> assertThat(knight.isMovable(source, Position.of(File.C, Rank.TWO))).isTrue()
        );
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class)
    @DisplayName("나이트가 이동할 수 없는 경우를 판단한다.")
    void knightInvalidMoveTest(Direction direction) {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        Position destination = direction.nextPosition(source);
        // when, then
        assertThat(knight.isMovable(source, destination)).isFalse();
    }
}
