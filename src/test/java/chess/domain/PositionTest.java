package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PositionTest {

    @DisplayName("방향에 따른 다음 위치를 결정한다")
    @Test
    public void nextPosition() {
        Position position = new Position(File.a, Rank.ONE);
        Position nextPosition = new Position(File.a, Rank.TWO);

        assertThat(position.next(Direction.NORTH)).isEqualTo(nextPosition);
    }

    @DisplayName("체스판 경계를 넘어갈 수 없다")
    @ParameterizedTest
    @MethodSource("nextPositionFailArguments")
    public void nextPositionFail(Position position, Direction direction) {
        assertThatCode(() -> position.next(direction))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> nextPositionFailArguments() {
        return Stream.of(
                Arguments.arguments(new Position(File.a, Rank.ONE), Direction.SOUTH,
                        new Position(File.a, Rank.EIGHT), Direction.NORTH,
                        new Position(File.h, Rank.ONE), Direction.SOUTH_EAST,
                        new Position(File.h, Rank.EIGHT), Direction.NORTH_EAST
                )
        );
    }
}
