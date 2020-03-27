package chess.domain.position;

import chess.domain.route.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {

    @Test
    void Position_생성() {
        assertThat(Position.of("b3")).isEqualTo(Position.of(File.b, Rank.THREE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"i1, a0, a9"})
    void 존재하지않는_Position_생성시_예외발생(String key) {
        assertThatThrownBy(() -> {
            Position.of(key);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 위치입니다.");
    }

    @ParameterizedTest
    @MethodSource("createPositionAndRank")
    void Position이_주어진_rank에_있는지_확인(Position position, Rank rank, boolean expected) {
        assertThat(position.isAt(rank)).isEqualTo(expected);
    }

    private static Stream<Arguments> createPositionAndRank() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Rank.ONE, true),
                Arguments.of(Position.of("a1"), Rank.THREE, false),

                Arguments.of(Position.of("b3"), Rank.THREE, true),
                Arguments.of(Position.of("b3"), Rank.ONE, false),

                Arguments.of(Position.of("h8"), Rank.EIGHT, true),
                Arguments.of(Position.of("h8"), Rank.THREE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndFile")
    void Position이_주어진_file에_있는지_확인(Position position, File file, boolean expected) {
        assertThat(position.isAt(file)).isEqualTo(expected);
    }


    private static Stream<Arguments> createPositionAndFile() {
        return Stream.of(
                Arguments.of(Position.of("a1"), File.a, true),
                Arguments.of(Position.of("a1"), File.c, false),

                Arguments.of(Position.of("b3"), File.b, true),
                Arguments.of(Position.of("b3"), File.a, false),

                Arguments.of(Position.of("h8"), File.h, true),
                Arguments.of(Position.of("h8"), File.a, false)
        );
    }

    @Test
    void reverse() {
    }

    @ParameterizedTest
    @MethodSource("createDirectionAndExpected")
    void 움직일_경우_새로운_Position을_반환(Direction direction, Position expected) {
        Position position = Position.of("d4");

        assertThat(position.moveTo(direction)).isEqualTo(expected);
    }

    private static Stream<Arguments> createDirectionAndExpected() {
        return Stream.of(
                Arguments.of(Direction.NORTH, Position.of("d5")),
                Arguments.of(Direction.NORTHEAST, Position.of("e5")),
                Arguments.of(Direction.NNE, Position.of("e6")),

                Arguments.of(Direction.SOUTH, Position.of("d3")),
                Arguments.of(Direction.SOUTHWEST, Position.of("c3")),
                Arguments.of(Direction.SSW, Position.of("c2"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndDirection")
    void 체스판_밖으로_움직일_경우_예외발생(Position position, Direction direction) {
        assertThatThrownBy(() -> {
            position.moveTo(direction);

        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 초과하였습니다.");
    }

    private static Stream<Arguments> createPositionAndDirection() {
        return Stream.of(
                Arguments.of(Position.of("d8"), Direction.NORTH),
                Arguments.of(Position.of("d1"), Direction.SOUTH),
                Arguments.of(Position.of("a4"), Direction.WEST),
                Arguments.of(Position.of("h4"), Direction.EAST)
        );
    }

    @Test
    void of() {
    }

    @Test
    void of1() {
    }

    @Test
    void isAt() {
    }

    @Test
    void isAt1() {
    }

    @Test
    void moveTo() {
        assertThat(Position.of("b3").moveTo(Direction.NORTH)).isEqualTo(Position.of("b4"));
    }
}
