package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PositionTest {

    private static Stream<Arguments> nextPositionFailArguments() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.ONE), Direction.SOUTH,
                        new Position(File.A, Rank.EIGHT), Direction.NORTH,
                        new Position(File.H, Rank.ONE), Direction.SOUTH_EAST,
                        new Position(File.H, Rank.EIGHT), Direction.NORTH_EAST
                )
        );
    }

    @DisplayName("방향에 따른 다음 위치를 결정한다")
    @Test
    public void nextPosition() {
        Position position = new Position(File.A, Rank.ONE);
        Position nextPosition = new Position(File.A, Rank.TWO);

        assertThat(position.next(Direction.NORTH)).isEqualTo(nextPosition);
    }

    @DisplayName("체스판 경계를 넘어갈 수 없다")
    @ParameterizedTest
    @MethodSource("nextPositionFailArguments")
    public void nextPositionFail(Position position, Direction direction) {
        assertThatThrownBy(() -> position.next(direction))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다음 방향으로 갈 수 없는지 알 수 있다.")
    @ParameterizedTest
    @MethodSource("nextPositionFailArguments")
    public void cantMoveNext(Position position, Direction direction) {
        assertThat(position.canMoveNext(direction)).isFalse();
    }

    @DisplayName("다음 방향으로 갈 수 있는지 알 수 있다.")
    @Test
    public void canMoveNext() {
        Position position = new Position(File.A, Rank.ONE);

        assertThat(position.canMoveNext(Direction.NORTH)).isTrue();
    }
}
