package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @ParameterizedTest
    @MethodSource("convertMovement")
    @DisplayName("convertMovement() : Position 이 움직일 때, 움직임의 최소 단위를 알 수 있다.")
    void test_convertMovement(final int fromColumn, final int fromRow,
                              final int toColumn, final int toRow, final Movement sourceMovement) {

        //given
        Position from = new Position(fromColumn, fromRow);
        Position to = new Position(toColumn, toRow);

        //then
        Movement targetMoveMent = to.convertMovement(from);

        //when
        assertEquals(sourceMovement, targetMoveMent);
    }

    static Stream<Arguments> convertMovement() {
        // Knight
        final int fromColumn1 = 1;
        final int fromRow1 = 1;
        final int toColumn1 = 3;
        final int toRow1 = 5;
        Movement movement1 = Movement.UUR;

        //오른쪽 이동
        final int fromColumn2 = 1;
        final int fromRow2 = 1;
        final int toColumn2 = 7;
        final int toRow2 = 1;
        Movement movement2 = Movement.R;

        //대각선 위 이동
        final int fromColumn3 = 4;
        final int fromRow3 = 2;
        final int toColumn3 = 5;
        final int toRow3 = 3;
        Movement movement3 = Movement.UR;

        return Stream.of(
                Arguments.of(fromColumn1, fromRow1, toColumn1, toRow1, movement1),
                Arguments.of(fromColumn2, fromRow2, toColumn2, toRow2, movement2),
                Arguments.of(fromColumn3, fromRow3, toColumn3, toRow3, movement3)
        );
    }

    @ParameterizedTest
    @MethodSource("moveBy")
    @DisplayName("moveBy() : movement에 따라서 Position 이 다음 위치로 이동할 수 있다.")
    void test_moveBy(final Position from, final Position to, Movement movement) throws Exception {
        //when & then
        assertEquals(from.moveBy(movement), to);
    }

    static Stream<Arguments> moveBy() {

        final Position from1 = new Position(7, 7);
        final Movement movement1 = Movement.UR;
        final Position to1 = new Position(8, 8);

        final Position from2 = new Position(8, 7);
        final Movement movement2 = Movement.U;
        final Position to2 = new Position(8, 8);

        final Position from3 = new Position(7, 8);
        final Movement movement3 = Movement.R;
        final Position to3 = new Position(8, 8);

        final Position from4 = new Position(6, 6);
        final Movement movement4 = Movement.UUR;
        final Position to4 = new Position(7, 8);

        return Stream.of(
                Arguments.of(from1, to1, movement1),
                Arguments.of(from2, to2, movement2),
                Arguments.of(from3, to3, movement3),
                Arguments.of(from4, to4, movement4)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateRow")
    @DisplayName("calculateRowBetween() : from, to Position의 row 차이를 구할 수 있다.")
    void test_calculateRowBetween(final Position from, final Position to, final int rowDiff) throws Exception {
        //when & then
        assertEquals(rowDiff, to.calculateRowBetween(from));
    }

    static Stream<Arguments> calculateRow() {

        final Position from1 = new Position(1, 1);
        final Position to1 = new Position(3, 5);
        final int rowDiff1 = 4;

        final Position from2 = new Position(1, 1);
        final Position to2 = new Position(7, 1);
        final int rowDiff2 = 0;

        final Position from3 = new Position(4, 2);
        final Position to3 = new Position(5, 3);
        final int rowDiff3 = 1;

        final Position from4 = new Position(4, 4);
        final Position to4 = new Position(5, 2);
        final int rowDiff4 = -2;

        return Stream.of(
                Arguments.of(from1, to1, rowDiff1),
                Arguments.of(from2, to2, rowDiff2),
                Arguments.of(from3, to3, rowDiff3),
                Arguments.of(from4, to4, rowDiff4)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateColumn")
    @DisplayName("calculateColumnBetween() : from, to Position의 column 차이를 구할 수 있다.")
    void test_calculateColumnBetween(final Position from, final Position to, final int columnDiff) throws Exception {
        //when & then
        assertEquals(columnDiff, to.calculateColumnBetween(from));
    }

    static Stream<Arguments> calculateColumn() {

        final Position from1 = new Position(1, 1);
        final Position to1 = new Position(3, 5);
        final int columnDiff1 = 2;

        final Position from2 = new Position(1, 1);
        final Position to2 = new Position(7, 1);
        final int columnDiff2 = 6;

        final Position from3 = new Position(4, 2);
        final Position to3 = new Position(5, 3);
        final int columnDiff3 = 1;

        final Position from4 = new Position(4, 4);
        final Position to4 = new Position(3, 2);
        final int columnDiff4 = -1;

        return Stream.of(
                Arguments.of(from1, to1, columnDiff1),
                Arguments.of(from2, to2, columnDiff2),
                Arguments.of(from3, to3, columnDiff3),
                Arguments.of(from4, to4, columnDiff4)
        );
    }
}
