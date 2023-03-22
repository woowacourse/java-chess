package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import chess.domain.square.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PieceDirectionTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("폰의 이동 가능 방향 테스트")
    class PawnTest {

        private final PieceDirection whitePawnDirection = PieceDirection.WHITE_PAWN;
        private final PieceDirection blackPawnDirection = PieceDirection.BLACK_PAWN;

        @ParameterizedTest
        @MethodSource("makeWhitePawnDirection")
        @DisplayName("흰색 폰 이동 가능 방향 테스트")
        void white_pawn_find_direction(int fileDifference, int rankDifference, Direction direction) {
            assertThat(whitePawnDirection.findDirection(fileDifference, rankDifference)).isEqualTo(direction);
        }

        private Stream<Arguments> makeWhitePawnDirection() {
            return Stream.of(
                    Arguments.of(0, 1, Direction.UP),
                    Arguments.of(-1, 1, Direction.UP_LEFT),
                    Arguments.of(1, 1, Direction.UP_RIGHT)
            );
        }

        @ParameterizedTest
        @CsvSource({"1,-1", "2,2", "0,-1"})
        @DisplayName("흰 폰 이상한 방향이면 실패한다.")
        void white_pawn_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> whitePawnDirection.findDirection(fileDirection, rankDirection))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @MethodSource("makeBlackPawnDirection")
        @DisplayName("검은색 폰 이동 가능 방향 테스트")
        void black_pawn_find_direction(int fileDifference, int rankDifference, Direction direction) {
            assertThat(blackPawnDirection.findDirection(fileDifference, rankDifference)).isEqualTo(direction);
        }

        private Stream<Arguments> makeBlackPawnDirection() {
            return Stream.of(
                    Arguments.of(0, -1, Direction.DOWN),
                    Arguments.of(-1, -1, Direction.DOWN_LEFT),
                    Arguments.of(1, -1, Direction.DOWN_RIGHT)
            );
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "0,1"})
        @DisplayName("검정 폰 이상한 방향이면 실패한다.")
        void black_pawn_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> blackPawnDirection.findDirection(fileDirection, rankDirection))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    @DisplayName("직선의 이동 가능 방향 테스트")
    class Straight {

        private final PieceDirection straightDirection = PieceDirection.STRAIGHT;

        @ParameterizedTest
        @CsvSource({"0,1", "0,2", "0,3", "0,6", "0,7"})
        @DisplayName("윗 방향 성공")
        void up(int zero, int upRank) {
            assertThat(straightDirection.findDirection(zero, upRank)).isEqualTo(Direction.UP);
        }

        @ParameterizedTest
        @DisplayName("아랫 방향 성공")
        @CsvSource({"0,-1", "0,-2", "0,-3", "0,-6", "0,-7"})
        void down(int zero, int downRank) {
            assertThat(straightDirection.findDirection(zero, downRank)).isEqualTo(Direction.DOWN);
        }

        @ParameterizedTest
        @DisplayName("왼쪽 방향 성공")
        @CsvSource({"-1,0", "-2,0", "-3,0", "-6,0", "-7,0"})
        void left(int leftFile, int zero) {
            assertThat(straightDirection.findDirection(leftFile, zero)).isEqualTo(Direction.LEFT);
        }

        @ParameterizedTest
        @DisplayName("오른쪽 방향 성공")
        @CsvSource({"1,0", "2,0", "3,0", "6,0", "7,0"})
        void right(int rightFile, int zero) {
            assertThat(straightDirection.findDirection(rightFile, zero)).isEqualTo(Direction.RIGHT);
        }

        @ParameterizedTest
        @DisplayName("직선 이동이 아니면 실패")
        @CsvSource({"1,1", "2,-2", "3,6", "6,3", "0,0"})
        void straight_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> straightDirection.findDirection(fileDirection, rankDirection))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    @DisplayName("대각선의 이동 가능 방향 테스트")
    class Diagonal {

        private final PieceDirection diagonalDirection = PieceDirection.DIAGONAL;

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "6,6", "7,7"})
        @DisplayName("UP RIGHT를 반환한다.")
        void up_right(int rightFile, int upRank) {
            assertThat(diagonalDirection.findDirection(rightFile, upRank)).isEqualTo(Direction.UP_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"1,-1", "2,-2", "3,-3", "6,-6", "7,-7"})
        @DisplayName("DOWN RIGHT를 반환한다.")
        void down_right(int rightFile, int downRank) {
            assertThat(diagonalDirection.findDirection(rightFile, downRank)).isEqualTo(Direction.DOWN_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"-1,-1", "-2,-2", "-3,-3", "-6,-6", "-7,-7"})
        @DisplayName("DOWN LEFT를 반환한다.")
        void down_left(int leftFile, int downRank) {
            assertThat(diagonalDirection.findDirection(leftFile, downRank)).isEqualTo(Direction.DOWN_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"-1,1", "-2,2", "-3,3", "-6,6", "-7,7"})
        @DisplayName("UP LEFT를 반환한다.")
        void up_left(int leftFile, int upRank) {
            assertThat(diagonalDirection.findDirection(leftFile, upRank)).isEqualTo(Direction.UP_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"-1,2", "0,2", "-4,3", "-2,6", "0,7"})
        @DisplayName("대각 이동이 아니면 실패")
        void diagonal_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> diagonalDirection.findDirection(fileDirection, rankDirection))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("나이트의 이동 가능 방향 테스트")
    class Knight {

        private final PieceDirection knightDirection = PieceDirection.KNIGHT;

        @ParameterizedTest
        @MethodSource("makeKnightDirection")
        @DisplayName("나이트 이동 가능 방향 테스트")
        void white_pawn_find_direction(int fileDifference, int rankDifference, Direction direction) {
            assertThat(knightDirection.findDirection(fileDifference, rankDifference)).isEqualTo(direction);
        }

        private Stream<Arguments> makeKnightDirection() {
            return Stream.of(
                    Arguments.of(1, 2, Direction.UP_UP_RIGHT),
                    Arguments.of(2, 1, Direction.UP_RIGHT_RIGHT),
                    Arguments.of(2, -1, Direction.DOWN_RIGHT_RIGHT),
                    Arguments.of(1, -2, Direction.DOWN_DOWN_RIGHT),
                    Arguments.of(-1, -2, Direction.DOWN_DOWN_LEFT),
                    Arguments.of(-2, -1, Direction.DOWN_LEFT_LEFT),
                    Arguments.of(-2, 1, Direction.UP_LEFT_LEFT),
                    Arguments.of(-1, 2, Direction.UP_UP_LEFT)
            );
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "0,3", "0,0"})
        @DisplayName("나이트 이동 실패 케이스")
        void knight_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> knightDirection.findDirection(fileDirection, rankDirection))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    @DisplayName("킹과 퀸 이동 가능 방향 테스트")
    class KingAndQueen {

        private final PieceDirection kingAndQueenDirection = PieceDirection.KING_AND_QUEEN;

        @ParameterizedTest
        @CsvSource({"-1,0", "-2,0", "-7,0"})
        @DisplayName("킹과 퀸 왼쪽 이동 테스트")
        void left(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.LEFT);
        }

        @ParameterizedTest
        @CsvSource({"1,0", "2,0", "7,0"})
        @DisplayName("킹과 퀸 오른쪽 이동 테스트")
        void right(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"0,1", "0,2", "0,7"})
        @DisplayName("킹과 퀸 윗 방향 이동 테스트")
        void up(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.UP);
        }

        @ParameterizedTest
        @CsvSource({"0,-1", "0,-2", "0,-7"})
        @DisplayName("킹과 퀸 아랫 방향 이동 테스트")
        void down(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.DOWN);
        }

        @ParameterizedTest
        @CsvSource({"-1,1", "-2,2", "-7,7"})
        @DisplayName("킹과 퀸 왼쪽 윗 방향 이동 테스트")
        void up_left(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.UP_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "7,7"})
        @DisplayName("킹과 퀸 오른쪽 윗 방향 이동 테스트")
        void up_right(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.UP_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"1,-1", "2,-2", "7,-7"})
        @DisplayName("킹과 퀸 오른쪽 아랫 방향 이동 테스트")
        void down_right(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.DOWN_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"-1,-1", "-2,-2", "-7,-7"})
        @DisplayName("킹과 퀸 왼쪽 아랫 방향 이동 테스트")
        void down_left(int fileDirection, int rankDirection) {
            assertThat(kingAndQueenDirection.findDirection(fileDirection, rankDirection)).isEqualTo(Direction.DOWN_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"-1,2", "3,2", "1,2"})
        @DisplayName("킹과 퀸 이동 실패 케이스")
        void fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> kingAndQueenDirection.findDirection(fileDirection, rankDirection))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물이 이동할 수 없는 위치입니다.");
        }
    }
}
