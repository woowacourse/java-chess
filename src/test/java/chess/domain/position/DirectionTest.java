package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

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
    void checkDirection(Position fromPosition, Position toPosition, Direction direction) {
        assertThat(Direction.judge(fromPosition, toPosition)).isEqualTo(direction);
    }

    private static Stream<Arguments> providePositionDirection() {
        return Stream.of(
            Arguments.of(Position.valueOf(File.A, Rank.ONE),
                Position.valueOf(File.B, Rank.ONE), Direction.RIGHT),
            Arguments.of(Position.valueOf(File.B, Rank.ONE),
                Position.valueOf(File.A, Rank.ONE), Direction.LEFT),
            Arguments.of(Position.valueOf(File.A, Rank.ONE),
                Position.valueOf(File.A, Rank.TWO), Direction.UP),
            Arguments.of(Position.valueOf(File.A, Rank.TWO),
                Position.valueOf(File.A, Rank.ONE), Direction.DOWN),
            Arguments.of(Position.valueOf(File.A, Rank.ONE),
                Position.valueOf(File.B, Rank.TWO), Direction.RIGHTUP),
            Arguments.of(Position.valueOf(File.A, Rank.TWO),
                Position.valueOf(File.B, Rank.ONE), Direction.RIGHTDOWN),
            Arguments.of(Position.valueOf(File.B, Rank.ONE),
                Position.valueOf(File.A, Rank.TWO), Direction.LEFTUP),
            Arguments.of(Position.valueOf(File.B, Rank.TWO),
                Position.valueOf(File.A, Rank.ONE), Direction.LEFTDOWN));
    }

    @Test
    @DisplayName("출발 Position에서 목적지 position으로 가는 경로 중 오른 쪽 위로 한칸 움직인 Position을 반환한다")
    void moveRightUpOneStep() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.C, Rank.THREE);
        Direction direction = Direction.judge(from, to);

        Position stepPosition = Direction.getIncreasedPositionByDirection(from, direction);

        assertThat(stepPosition).isEqualTo(Position.valueOf(File.B, Rank.TWO));
    }

    @Test
    @DisplayName("출발 Position에서 목적지 position으로 가는 경로 중 위로 한칸 움직인 Position을 반환한다")
    void moveUpOneStep() {
        Position from = Position.valueOf(File.A, Rank.ONE);
        Position to = Position.valueOf(File.A, Rank.THREE);
        Direction direction = Direction.judge(from, to);

        Position stepPosition = Direction.getIncreasedPositionByDirection(from, direction);

        assertThat(stepPosition).isEqualTo(Position.valueOf(File.A, Rank.TWO));
    }
}
