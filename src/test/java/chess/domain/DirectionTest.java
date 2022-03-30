package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DirectionTest {

    @ParameterizedTest
    @MethodSource("providePositionDirection")
    @DisplayName("출발 Position에서 목적지 Position으로 가는 방향을 반환하여 확인한다.")
    void checkDirection(Position fromPosition,Position toPosition,Direction direction) {
        assertThat(Direction.giveDirection(fromPosition,toPosition)).isEqualTo(direction);
    }

    private static Stream<Arguments> providePositionDirection() {
        return Stream.of(
            Arguments.of(Position.valueOf(File.a, Rank.ONE),
                Position.valueOf(File.b, Rank.ONE), Direction.RIGHT),
            Arguments.of(Position.valueOf(File.b, Rank.ONE),
                Position.valueOf(File.a, Rank.ONE), Direction.LEFT),
            Arguments.of(Position.valueOf(File.a, Rank.ONE),
                Position.valueOf(File.a, Rank.TWO), Direction.UP),
            Arguments.of(Position.valueOf(File.a, Rank.TWO),
                Position.valueOf(File.a, Rank.ONE), Direction.DOWN),
            Arguments.of(Position.valueOf(File.a, Rank.ONE),
                Position.valueOf(File.b, Rank.TWO), Direction.RIGHTUP),
            Arguments.of(Position.valueOf(File.a, Rank.TWO),
                Position.valueOf(File.b, Rank.ONE), Direction.RIGHTDOWN),
            Arguments.of(Position.valueOf(File.b, Rank.ONE),
                Position.valueOf(File.a, Rank.TWO), Direction.LEFTUP),
            Arguments.of(Position.valueOf(File.b, Rank.TWO),
                Position.valueOf(File.a, Rank.ONE), Direction.LEFTDOWN));
    }

    @Test
    @DisplayName("출발 Position에서 목적지 position으로 가는 경로 중 오른 쪽 위로 한칸 움직인 Position을 반환한다")
    void moveRightUpOneStep() {
        Position from = Position.valueOf(File.a, Rank.ONE);
        Position to = Position.valueOf(File.c, Rank.THREE);
        Direction direction = Direction.giveDirection(from, to);

        Position stepPosition = Direction.step(from, direction);

        assertThat(stepPosition).isEqualTo(Position.valueOf(File.b, Rank.TWO));
    }

    @Test
    @DisplayName("출발 Position에서 목적지 position으로 가는 경로 중 위로 한칸 움직인 Position을 반환한다")
    void moveUpOneStep() {
        Position from = Position.valueOf(File.a, Rank.ONE);
        Position to = Position.valueOf(File.a, Rank.THREE);
        Direction direction = Direction.giveDirection(from, to);

        Position stepPosition = Direction.step(from, direction);

        assertThat(stepPosition).isEqualTo(Position.valueOf(File.a, Rank.TWO));
    }
}
