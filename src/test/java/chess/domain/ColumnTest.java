package chess.domain;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.Column.*;
import static chess.domain.direction.BasicDirection.*;
import static org.assertj.core.api.Assertions.assertThat;

class ColumnTest {
    @DisplayName("세로 입력값(String)에 대한 index 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"8:0", "7:1", "6:2", "5:3", "4:4", "3:5", "2:6", "1:7"}, delimiter = ':')
    void fromString(final String type, final int index) {
        // given
        final Column columnByString = Column.from(type);
        final Column columnByInteger = Column.from(index);

        // expect
        assertThat(columnByString).isEqualTo(columnByInteger);
    }

    @DisplayName("세로 입력값(Integer)에 대한 index 반환 테스트")
    @ParameterizedTest
    @MethodSource("fromIntegerDummy")
    void fromInteger(final int index, final Column expected) {
        // given & when
        final Column columnByInteger = Column.from(index);

        // then
        assertThat(columnByInteger).isEqualTo(expected);
    }

    static Stream<Arguments> fromIntegerDummy() {
        return Stream.of(
                Arguments.arguments(0, COLUMN_0),
                Arguments.arguments(1, COLUMN_1),
                Arguments.arguments(2, COLUMN_2),
                Arguments.arguments(3, COLUMN_3),
                Arguments.arguments(4, COLUMN_4),
                Arguments.arguments(5, COLUMN_5),
                Arguments.arguments(6, COLUMN_6),
                Arguments.arguments(7, COLUMN_7)
        );
    }

    @DisplayName("방향을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:0", "0:1", "1:1"}, delimiter = ':')
    void findDirection(final int index, final int otherIndex) {
        // given
        final Column source = Column.from(index);
        final Column target = Column.from(otherIndex);
        final int expect = otherIndex - index;

        // when
        final int direction = source.findDirection(target);

        // then
        assertThat(direction).isEqualTo(expect);
    }
    
    @DisplayName("움직인다.")
    @ParameterizedTest
    @MethodSource("moveDummy")
    void move(final Column column, final BasicDirection direction, final Column expect) {
        // given & when
        final Column movedColumn = column.move(direction);

        // then
        assertThat(movedColumn).isEqualTo(expect);
    }

    static Stream<Arguments> moveDummy() {
        return Stream.of(
                Arguments.arguments(COLUMN_1, NORTH, COLUMN_0),
                Arguments.arguments(COLUMN_1, SOUTH, COLUMN_2),
                Arguments.arguments(COLUMN_3, NORTH, COLUMN_2),
                Arguments.arguments(COLUMN_3, SOUTH, COLUMN_4)
        );
    }

    @DisplayName("다음 움직임이 가능한 범위인지 확인한다.")
    @ParameterizedTest
    @MethodSource("isMovableDummy")
    void isMovable(final Column column, final Direction direction, final boolean expected) {
        // given & when
        final boolean isMovable = column.isMovable(direction);

        // then
        assertThat(isMovable).isEqualTo(expected);
    }

    static Stream<Arguments> isMovableDummy() {
        return Stream.of(
                Arguments.arguments(COLUMN_3, NORTH, true),
                Arguments.arguments(COLUMN_3, EAST, true),
                Arguments.arguments(COLUMN_3, WEST, true),
                Arguments.arguments(COLUMN_3, SOUTH, true),
                Arguments.arguments(COLUMN_3, NORTH_EAST, true),
                Arguments.arguments(COLUMN_3, NORTH_WEST, true),
                Arguments.arguments(COLUMN_3, SOUTH_EAST, true),
                Arguments.arguments(COLUMN_3, SOUTH_WEST, true),
                Arguments.arguments(COLUMN_0, NORTH, false),
                Arguments.arguments(COLUMN_0, WEST, true),
                Arguments.arguments(COLUMN_0, NORTH_WEST, false),
                Arguments.arguments(COLUMN_0, NORTH_EAST, false),
                Arguments.arguments(COLUMN_0, SOUTH_WEST, true),
                Arguments.arguments(COLUMN_7, SOUTH_EAST, false),
                Arguments.arguments(COLUMN_7, EAST, true),
                Arguments.arguments(COLUMN_7, SOUTH, false)
        );
    }

    @DisplayName("둘의 차이를 계산한다.")
    @ParameterizedTest
    @MethodSource("diffDummy")
    void diff(final Column column, final Column otherColumn, int expected) {
        // given & when
        final int diffRow = column.diff(otherColumn);

        // then
        assertThat(diffRow).isEqualTo(expected);
    }

    static Stream<Arguments> diffDummy() {
        return Stream.of(
                Arguments.arguments(COLUMN_7, COLUMN_7, 0),
                Arguments.arguments(COLUMN_7, COLUMN_6, 1),
                Arguments.arguments(COLUMN_7, COLUMN_5, 2),
                Arguments.arguments(COLUMN_7, COLUMN_4, 3),
                Arguments.arguments(COLUMN_7, COLUMN_3, 4),
                Arguments.arguments(COLUMN_7, COLUMN_2, 5),
                Arguments.arguments(COLUMN_7, COLUMN_1, 6),
                Arguments.arguments(COLUMN_7, COLUMN_0, 7)
        );
    }
}
