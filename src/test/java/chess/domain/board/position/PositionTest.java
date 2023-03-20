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
    void test_convertMovement(final int fromFile, final int fromRank,
                              final int toFile, final int toRank, final Movement sourceMovement) {

        //given
        Position from = new Position(fromFile, fromRank);
        Position to = new Position(toFile, toRank);

        //then
        Movement targetMoveMent = to.convertMovement(from);

        //when
        assertEquals(sourceMovement, targetMoveMent);
    }

    static Stream<Arguments> convertMovement() {
        // Knight
        final int fromFile1 = 1;
        final int fromRank1 = 1;
        final int toFile1 = 3;
        final int toRank1 = 5;
        Movement movement1 = Movement.UUR;

        //오른쪽 이동
        final int fromFile2 = 1;
        final int fromRank2 = 1;
        final int toFile2 = 7;
        final int toRank2 = 1;
        Movement movement2 = Movement.R;

        //대각선 위 이동
        final int fromFile3 = 4;
        final int fromRank3 = 2;
        final int toFile3 = 5;
        final int toRank3 = 3;
        Movement movement3 = Movement.UR;

        return Stream.of(
                Arguments.of(fromFile1, fromRank1, toFile1, toRank1, movement1),
                Arguments.of(fromFile2, fromRank2, toFile2, toRank2, movement2),
                Arguments.of(fromFile3, fromRank3, toFile3, toRank3, movement3)
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
    @MethodSource("calculateRank")
    @DisplayName("calculateRankBetween() : from, to Position의 Rank 차이를 구할 수 있다.")
    void test_calculateRankBetween(final Position from, final Position to, final int rankDiff) throws Exception {
        //when & then
        assertEquals(rankDiff, to.calculateRankBetween(from));
    }

    static Stream<Arguments> calculateRank() {

        final Position from1 = new Position(1, 1);
        final Position to1 = new Position(3, 5);
        final int rankDiff1 = 4;

        final Position from2 = new Position(1, 1);
        final Position to2 = new Position(7, 1);
        final int rankDiff2 = 0;

        final Position from3 = new Position(4, 2);
        final Position to3 = new Position(5, 3);
        final int rankDiff3 = 1;

        final Position from4 = new Position(4, 4);
        final Position to4 = new Position(5, 2);
        final int rankDiff4 = -2;

        return Stream.of(
                Arguments.of(from1, to1, rankDiff1),
                Arguments.of(from2, to2, rankDiff2),
                Arguments.of(from3, to3, rankDiff3),
                Arguments.of(from4, to4, rankDiff4)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateFile")
    @DisplayName("calculateFileBetween() : from, to Position의 File 차이를 구할 수 있다.")
    void test_calculateFileBetween(final Position from, final Position to, final int fileDiff) throws Exception {
        //when & then
        assertEquals(fileDiff, to.calculateFileBetween(from));
    }

    static Stream<Arguments> calculateFile() {

        final Position from1 = new Position(1, 1);
        final Position to1 = new Position(3, 5);
        final int fileDiff1 = 2;

        final Position from2 = new Position(1, 1);
        final Position to2 = new Position(7, 1);
        final int fileDiff2 = 6;

        final Position from3 = new Position(4, 2);
        final Position to3 = new Position(5, 3);
        final int fileDiff3 = 1;

        final Position from4 = new Position(4, 4);
        final Position to4 = new Position(3, 2);
        final int fileDiff4 = -1;

        return Stream.of(
                Arguments.of(from1, to1, fileDiff1),
                Arguments.of(from2, to2, fileDiff2),
                Arguments.of(from3, to3, fileDiff3),
                Arguments.of(from4, to4, fileDiff4)
        );
    }
}
