package chess.domain;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.Row.*;
import static chess.domain.direction.BasicDirection.*;
import static org.assertj.core.api.Assertions.assertThat;

class RowTest {
    @DisplayName("가로 입력값에 대한 index 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"a:0", "b:1", "c:2", "d:3", "e:4", "f:5", "g:6", "h:7"}, delimiter = ':')
    void from(final String type, final int index) {
        // given & when
        final Row rowByString = Row.from(type);
        final Row rowByInteger = Row.from(index);
        
        // then
        assertThat(rowByString).isEqualTo(rowByInteger);
    }

    @DisplayName("방향을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:0", "0:1", "1:1"}, delimiter = ':')
    void findDirection(final int index, final int otherIndex) {
        // given
        final Row source = Row.from(index);
        final Row target = Row.from(otherIndex);
        final int expect = otherIndex - index;

        // when
        final int direction = source.findDirection(target);

        // then
        assertThat(direction).isEqualTo(expect);
    }

    @DisplayName("움직인다.")
    @ParameterizedTest
    @MethodSource("moveDummy")
    void move(final Row row, final BasicDirection direction, final Row expect) {
        // given & when
        final Row movedRow = row.move(direction);

        // then
        assertThat(movedRow).isEqualTo(expect);
    }

    static Stream<Arguments> moveDummy() {
        return Stream.of(
                Arguments.arguments(ROW_1, WEST, ROW_0),
                Arguments.arguments(ROW_1, EAST, ROW_2),
                Arguments.arguments(ROW_3, WEST, ROW_2),
                Arguments.arguments(ROW_3, EAST, ROW_4)
        );
    }

    @DisplayName("다음 움직임이 가능한 범위인지 확인한다.")
    @ParameterizedTest
    @MethodSource("isMovableDummy")
    void isMovable(final Row row, final Direction direction, final boolean expected) {
        // given & when
        final boolean isMovable = row.isMovable(direction);

        // then
        assertThat(isMovable).isEqualTo(expected);
    }

    static Stream<Arguments> isMovableDummy() {
        return Stream.of(
                Arguments.arguments(ROW_3, NORTH, true),
                Arguments.arguments(ROW_3, EAST, true),
                Arguments.arguments(ROW_3, WEST, true),
                Arguments.arguments(ROW_3, SOUTH, true),
                Arguments.arguments(ROW_3, NORTH_EAST, true),
                Arguments.arguments(ROW_3, NORTH_WEST, true),
                Arguments.arguments(ROW_3, SOUTH_EAST, true),
                Arguments.arguments(ROW_3, SOUTH_WEST, true),
                Arguments.arguments(ROW_0, NORTH, true),
                Arguments.arguments(ROW_0, WEST, false),
                Arguments.arguments(ROW_0, NORTH_WEST, false),
                Arguments.arguments(ROW_0, NORTH_EAST, true),
                Arguments.arguments(ROW_0, SOUTH_WEST, false),
                Arguments.arguments(ROW_7, SOUTH_EAST, false),
                Arguments.arguments(ROW_7, EAST, false),
                Arguments.arguments(ROW_7, SOUTH, true)
        );
    }

    @DisplayName("둘의 차이를 계산한다.")
    @ParameterizedTest
    @MethodSource("diffDummy")
    void diff(final Row row, final Row otherRow, int expected) {
        // given & when
        final int diffRow = row.diff(otherRow);

        // then
        assertThat(diffRow).isEqualTo(expected);
    }

    static Stream<Arguments> diffDummy() {
        return Stream.of(
                Arguments.arguments(ROW_7, ROW_7, 0),
                Arguments.arguments(ROW_7, ROW_6, 1),
                Arguments.arguments(ROW_7, ROW_5, 2),
                Arguments.arguments(ROW_7, ROW_4, 3),
                Arguments.arguments(ROW_7, ROW_3, 4),
                Arguments.arguments(ROW_7, ROW_2, 5),
                Arguments.arguments(ROW_7, ROW_1, 6),
                Arguments.arguments(ROW_7, ROW_0, 7)
        );
    }
}
