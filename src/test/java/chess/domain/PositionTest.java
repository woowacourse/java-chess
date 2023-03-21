package chess.domain;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.Column.*;
import static chess.domain.Row.*;
import static chess.domain.direction.BasicDirection.*;
import static chess.domain.direction.KnightDirection.*;
import static chess.mock.MockPosition.*;
import static org.assertj.core.api.Assertions.*;

class PositionTest {
    @DisplayName("생성한다.")
    @Test
    void create() {
        // expect
        assertThatNoException().isThrownBy(() -> Position.of(ROW_1, COLUMN_1));
    }

    @DisplayName("가로 또는 세로 값이 7보다 클 경우 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:8", "1:9", "5:9", "8:1", "8:8"}, delimiter = ':')
    void throwExceptionWhenRowOrColumnIsGreaterThan7(final int row, final int column) {
        // expect
        assertThatThrownBy(() -> Position.of(Row.from(row), Column.from(column)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 문자열로 부터 Position 값을 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1:0:7", "b1:1:7", "d4:3:4"}, delimiter = ':')
    void from(final String rowColumn, final int row, final int column) {
        // given & when
        final Position findPosition = Position.from(rowColumn);

        // then
        assertThat(findPosition)
                .hasFieldOrPropertyWithValue("row", Row.from(row))
                .hasFieldOrPropertyWithValue("column", Column.from(column));
    }

    @DisplayName("논리적으로 같을 때 동등한지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0:0:0", "4:2:4:2", "1:3:1:3", "4:6:4:6"}, delimiter = ':')
    void equals(final int rowOne, final int columnOne, final int rowTwo, final int columnTwo) {
        // given & when
        final Position positionOne = Position.of(Row.from(rowOne), Column.from(columnOne));
        final Position positionTwo = Position.of(Row.from(rowTwo), Column.from(columnTwo));

        // then
        assertThat(positionOne).isEqualTo(positionTwo);
    }

    @DisplayName("column 내림차순 -> row 내림차순으로 비교되는지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0:0:0:0", "4:2:1:0:1", "1:3:5:3:-1", "5:3:7:7:-1"}, delimiter = ':')
    void compareTo(final int rowOne, final int columnOne, final int rowTwo, final int columnTwo, final int expectedCompare) {
        // given
        final Position positionOne = Position.of(Row.from(rowOne), Column.from(columnOne));
        final Position positionTwo = Position.of(Row.from(rowTwo), Column.from(columnTwo));

        // when
        final int compareResult = positionOne.compareTo(positionTwo);

        // then
        assertThat(compareResult).isEqualTo(expectedCompare);
    }

    @DisplayName("가로 기준으로 방향을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"0:-1", "1:-1", "2:0", "3:1", "4:1"}, delimiter = ':')
    void findRowDirection(final int rowValue, final int expectDirection) {
        // given
        final Row row = Row.from(rowValue);

        // when
        final int direction = POSITION_2_2.findDirection(row);

        // then
        assertThat(direction).isEqualTo(expectDirection);
    }

    @DisplayName("세로 기준으로 방향을 찾는다.")
    @ParameterizedTest
    @CsvSource(value = {"0:-1", "1:-1", "2:0", "3:1", "4:1"}, delimiter = ':')
    void findColumnDirection(final int columnValue, final int expectDirection) {
        // given
        final Column column = Column.from(columnValue);

        // when
        final int direction = POSITION_2_2.findDirection(column);

        // then
        assertThat(direction).isEqualTo(expectDirection);
    }

    @DisplayName("방향으로 움직이다")
    @ParameterizedTest
    @MethodSource("moveDirectionDummy")
    void moveDirection(final Position position, final BasicDirection direction, final Position expected) {
        // given & when
        final Position movedPosition = position.move(direction);

        // then
        assertThat(movedPosition).isEqualTo(expected);
    }

    static Stream<Arguments> moveDirectionDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_1_1, WEST, POSITION_0_1),
                Arguments.arguments(POSITION_1_1, EAST, POSITION_2_1),
                Arguments.arguments(POSITION_1_1, NORTH, POSITION_1_0),
                Arguments.arguments(POSITION_1_1, SOUTH, POSITION_1_2),
                Arguments.arguments(POSITION_1_1, NORTH_WEST, POSITION_0_0),
                Arguments.arguments(POSITION_1_1, NORTH_EAST, POSITION_2_0),
                Arguments.arguments(POSITION_1_1, SOUTH_WEST, POSITION_0_2),
                Arguments.arguments(POSITION_1_1, SOUTH_EAST, POSITION_2_2)
        );
    }

    @DisplayName("다음 움직임이 가능한 범위인지 확인한다.")
    @ParameterizedTest
    @MethodSource("isMovableDummy")
    void isMovable(final Position current, final Direction direction, final boolean expected) {
        // given & when
        final boolean isMovable = current.isRangeOk(direction);

        // then
        assertThat(isMovable).isEqualTo(expected);
    }

    static Stream<Arguments> isMovableDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_3_3, NORTH, true),
                Arguments.arguments(POSITION_3_3, EAST, true),
                Arguments.arguments(POSITION_3_3, WEST, true),
                Arguments.arguments(POSITION_3_3, SOUTH, true),
                Arguments.arguments(POSITION_3_3, NORTH_EAST, true),
                Arguments.arguments(POSITION_3_3, NORTH_WEST, true),
                Arguments.arguments(POSITION_3_3, SOUTH_EAST, true),
                Arguments.arguments(POSITION_3_3, SOUTH_WEST, true),

                Arguments.arguments(POSITION_0_0, NORTH, false),
                Arguments.arguments(POSITION_0_0, WEST, false),
                Arguments.arguments(POSITION_0_0, NORTH_WEST, false),
                Arguments.arguments(POSITION_0_0, NORTH_EAST, false),
                Arguments.arguments(POSITION_0_0, SOUTH_WEST, false),
                Arguments.arguments(POSITION_7_7, SOUTH_EAST, false),
                Arguments.arguments(POSITION_7_7, EAST, false),
                Arguments.arguments(POSITION_7_7, SOUTH, false),

                Arguments.arguments(POSITION_3_3, NORTH_NORTH_WEST, true),
                Arguments.arguments(POSITION_3_3, NORTH_NORTH_EAST, true),
                Arguments.arguments(POSITION_3_3, WEST_WEST_NORTH, true),
                Arguments.arguments(POSITION_3_3, WEST_WEST_SOUTH, true),
                Arguments.arguments(POSITION_3_3, EAST_EAST_NORTH, true),
                Arguments.arguments(POSITION_3_3, EAST_EAST_SOUTH, true),
                Arguments.arguments(POSITION_3_3, SOUTH_SOUTH_EAST, true),
                Arguments.arguments(POSITION_3_3, SOUTH_SOUTH_WEST, true),

                Arguments.arguments(POSITION_0_0, NORTH_NORTH_WEST, false),
                Arguments.arguments(POSITION_0_0, NORTH_NORTH_EAST, false),
                Arguments.arguments(POSITION_0_0, WEST_WEST_NORTH, false),
                Arguments.arguments(POSITION_0_0, WEST_WEST_SOUTH, false),
                Arguments.arguments(POSITION_0_0, EAST_EAST_NORTH, false),
                Arguments.arguments(POSITION_7_7, EAST_EAST_SOUTH, false),
                Arguments.arguments(POSITION_7_7, SOUTH_SOUTH_EAST, false),
                Arguments.arguments(POSITION_7_7, SOUTH_SOUTH_WEST, false)
        );
    }

    @DisplayName("Column 차이를 계산한다.")
    @ParameterizedTest
    @MethodSource("diffColumnDummy")
    void diffColumn(final Position current, final Column column, int expected) {
        // given & when
        final int diffRow = current.diff(column);

        // then
        assertThat(diffRow).isEqualTo(expected);
    }

    static Stream<Arguments> diffColumnDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_0_7, COLUMN_7, 0),
                Arguments.arguments(POSITION_0_7, COLUMN_6, 1),
                Arguments.arguments(POSITION_0_7, COLUMN_5, 2),
                Arguments.arguments(POSITION_0_7, COLUMN_4, 3),
                Arguments.arguments(POSITION_0_7, COLUMN_3, 4),
                Arguments.arguments(POSITION_0_7, COLUMN_2, 5),
                Arguments.arguments(POSITION_0_7, COLUMN_1, 6),
                Arguments.arguments(POSITION_0_7, COLUMN_0, 7)
        );
    }

    @DisplayName("Row 차이를 계산한다.")
    @ParameterizedTest
    @MethodSource("diffRowDummy")
    void diffRow(final Position current, final Row row, int expected) {
        // given & when
        final int diffRow = current.diff(row);

        // then
        assertThat(diffRow).isEqualTo(expected);
    }

    static Stream<Arguments> diffRowDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_7_0, ROW_7, 0),
                Arguments.arguments(POSITION_7_0, ROW_6, 1),
                Arguments.arguments(POSITION_7_0, ROW_5, 2),
                Arguments.arguments(POSITION_7_0, ROW_4, 3),
                Arguments.arguments(POSITION_7_0, ROW_3, 4),
                Arguments.arguments(POSITION_7_0, ROW_2, 5),
                Arguments.arguments(POSITION_7_0, ROW_1, 6),
                Arguments.arguments(POSITION_7_0, ROW_0, 7)
        );
    }
}
