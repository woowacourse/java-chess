package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @DisplayName("방향에 따른 다음 위치를 결정한다")
    @Test
    void nextPosition() {
        Position position = PositionFixture.A1;
        Position nextPosition = PositionFixture.A2;

        assertThat(position.next(Direction.NORTH)).isEqualTo(nextPosition);
    }

    @DisplayName("체스판 경계를 넘어갈 수 없다")
    @ParameterizedTest
    @MethodSource("nextPositionFailArguments")
    void nextPositionFail(Position position, Direction direction) {
        assertThatThrownBy(() -> position.next(direction))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다음 방향으로 갈 수 없는지 알 수 있다.")
    @ParameterizedTest
    @MethodSource("nextPositionFailArguments")
    void cantMoveNext(Position position, Direction direction) {
        assertThat(position.canMoveNext(direction)).isFalse();
    }

    @DisplayName("다음 방향으로 갈 수 있는지 알 수 있다.")
    @Test
    void canMoveNext() {
        Position position = PositionFixture.A1;

        assertThat(position.canMoveNext(Direction.NORTH)).isTrue();
    }

    @DisplayName("문자열을 받아 Position으로 변환할 수 있다.")
    @Test
    void convert() {
        String source = "a1";
        Position position = Position.convert(source);

        assertThat(position).isEqualTo(PositionFixture.A1);
    }

    @DisplayName("잘못된 문자열을 받으면 Position으로 변환할 수 없다.")
    @Test
    void convertFail() {
        String source = "pobi";
        assertThatThrownBy(() -> Position.convert(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> nextPositionFailArguments() {
        return Stream.of(
                Arguments.arguments(PositionFixture.A1, Direction.SOUTH,
                        PositionFixture.A8, Direction.NORTH,
                        PositionFixture.H1, Direction.SOUTH_EAST,
                        PositionFixture.H8, Direction.NORTH_EAST
                )
        );
    }
}
