package chess.domain.piece;

import chess.domain.exception.WrongDirectionException;
import chess.domain.square.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.piece.PieceDirection.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceDirectionTest {
    
    @Nested
    @DisplayName("폰의 이동 가능 방향 테스트")
    class Pawn {

        @Test
        @DisplayName("흰 폰 위로 가기 성공")
        void up_white() {
            assertThat(findWhitePawnDirection(0, 1)).isEqualTo(Direction.UP);
        }

        @Test
        @DisplayName("흰 폰 왼쪽위로 가기 성공")
        void up_left_white() {
            assertThat(findWhitePawnDirection(-1, 1)).isEqualTo(Direction.UP_LEFT);
        }

        @Test
        @DisplayName("흰 폰 오른쪽위로 가기 성공")
        void up_right_white() {
            assertThat(findWhitePawnDirection(1, 1)).isEqualTo(Direction.UP_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"1,-1", "2,2", "0,-1"})
        @DisplayName("흰 폰 이상한 방향이면 실패한다.")
        void white_pawn_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> findWhitePawnDirection(fileDirection, rankDirection))
                    .isInstanceOf(WrongDirectionException.class);
        }

        @Test
        @DisplayName("검정 폰 아래로 가기 성공")
        void down_black() {
            assertThat(findBlackPawnDirection(0, -1)).isEqualTo(Direction.DOWN);
        }

        @Test
        @DisplayName("검정 폰 왼쪽아래로 가기 성공")
        void down_left_black() {
            assertThat(findBlackPawnDirection(-1, -1)).isEqualTo(Direction.DOWN_LEFT);
        }

        @Test
        @DisplayName("검정 폰 오른쪽아래로 가기 성공")
        void down_right_black() {
            assertThat(findBlackPawnDirection(1, -1)).isEqualTo(Direction.DOWN_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "0,1"})
        @DisplayName("검정 폰 이상한 방향이면 실패한다.")
        void black_pawn_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> findBlackPawnDirection(fileDirection, rankDirection))
                    .isInstanceOf(WrongDirectionException.class);
        }
    }

    @Nested
    @DisplayName("직선의 이동 가능 방향 테스트")
    class Straight {

        @ParameterizedTest
        @CsvSource({"0,1", "0,2", "0,3", "0,6", "0,7"})
        @DisplayName("윗 방향 성공")
        void up(int zero, int upRank) {
            assertThat(findStraightDirection(zero, upRank)).isEqualTo(Direction.UP);
        }

        @ParameterizedTest
        @DisplayName("아랫 방향 성공")
        @CsvSource({"0,-1", "0,-2", "0,-3", "0,-6", "0,-7"})
        void down(int zero, int downRank) {
            assertThat(findStraightDirection(zero, downRank)).isEqualTo(Direction.DOWN);
        }

        @ParameterizedTest
        @DisplayName("왼쪽 방향 성공")
        @CsvSource({"-1,0", "-2,0", "-3,0", "-6,0", "-7,0"})
        void left(int leftFile, int zero) {
            assertThat(findStraightDirection(leftFile, zero)).isEqualTo(Direction.LEFT);
        }

        @ParameterizedTest
        @DisplayName("오른쪽 방향 성공")
        @CsvSource({"1,0", "2,0", "3,0", "6,0", "7,0"})
        void right(int rightFile, int zero) {
            assertThat(findStraightDirection(rightFile, zero)).isEqualTo(Direction.RIGHT);
        }

        @ParameterizedTest
        @DisplayName("직선 이동이 아니면 실패")
        @CsvSource({"1,1", "2,-2", "3,6", "6,3", "0,0"})
        void straight_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> findStraightDirection(fileDirection, rankDirection))
                    .isInstanceOf(WrongDirectionException.class);
        }
    }

    @Nested
    @DisplayName("대각선의 이동 가능 방향 테스트")
    class Diagonal {

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "6,6", "7,7"})
        @DisplayName("UP RIGHT를 반환한다.")
        void up_right(int rightFile, int upRank) {
            assertThat(findDiagonalDirection(rightFile, upRank)).isEqualTo(Direction.UP_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"1,-1", "2,-2", "3,-3", "6,-6", "7,-7"})
        @DisplayName("DOWN RIGHT를 반환한다.")
        void down_right(int rightFile, int downRank) {
            assertThat(findDiagonalDirection(rightFile, downRank)).isEqualTo(Direction.DOWN_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"-1,-1", "-2,-2", "-3,-3", "-6,-6", "-7,-7"})
        @DisplayName("DOWN LEFT를 반환한다.")
        void down_left(int leftFile, int downRank) {
            assertThat(findDiagonalDirection(leftFile, downRank)).isEqualTo(Direction.DOWN_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"-1,1", "-2,2", "-3,3", "-6,6", "-7,7"})
        @DisplayName("UP LEFT를 반환한다.")
        void up_left(int leftFile, int upRank) {
            assertThat(findDiagonalDirection(leftFile, upRank)).isEqualTo(Direction.UP_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"-1,2", "0,2", "-4,3", "-2,6", "0,7"})
        @DisplayName("대각 이동이 아니면 실패")
        void diagonal_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> findDiagonalDirection(fileDirection, rankDirection))
                    .isInstanceOf(WrongDirectionException.class);
        }
    }

    @Nested
    @DisplayName("나이트의 이동 가능 방향 테스트")
    class Knight {

        @Test
        @DisplayName("위로 두칸, 오른쪽으로 한칸 이동")
        void up_up_right() {
            assertThat(findKnightDirection(1, 2)).isEqualTo(Direction.UP_UP_RIGHT);
        }

        @Test
        @DisplayName("위로 한칸, 오른쪽으로 두칸 이동")
        void up_right_right() {
            assertThat(findKnightDirection(2, 1)).isEqualTo(Direction.UP_RIGHT_RIGHT);
        }

        @Test
        @DisplayName("아래로 한칸, 오른쪽으로 두칸 이동")
        void down_right_right() {
            assertThat(findKnightDirection(2, -1)).isEqualTo(Direction.DOWN_RIGHT_RIGHT);
        }

        @Test
        @DisplayName("아래로 두칸, 오른쪽으로 한칸 이동")
        void down_down_right() {
            assertThat(findKnightDirection(1, -2)).isEqualTo(Direction.DOWN_DOWN_RIGHT);
        }

        @Test
        @DisplayName("아래로 두칸, 왼쪽으로 한칸 이동")
        void down_down_left() {
            assertThat(findKnightDirection(-1, -2)).isEqualTo(Direction.DOWN_DOWN_LEFT);
        }

        @Test
        @DisplayName("아래로 한칸, 왼쪽으로 두칸 이동")
        void down_left_left() {
            assertThat(findKnightDirection(-2, -1)).isEqualTo(Direction.DOWN_LEFT_LEFT);
        }

        @Test
        @DisplayName("위로 한칸, 왼쪽으로 두칸 이동")
        void up_left_left() {
            assertThat(findKnightDirection(-2, 1)).isEqualTo(Direction.UP_LEFT_LEFT);
        }

        @Test
        @DisplayName("위로 두칸, 왼쪽으로 한칸 이동")
        void up_up_left() {
            assertThat(findKnightDirection(-1, 2)).isEqualTo(Direction.UP_UP_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "0,3", "0,0"})
        @DisplayName("나이트 이동 실패 케이스")
        void knight_fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> findKnightDirection(fileDirection, rankDirection))
                    .isInstanceOf(WrongDirectionException.class);
        }
    }

    @Nested
    @DisplayName("킹과 퀸 이동 가능 방향 테스트")
    class KingAndQueen {

        @ParameterizedTest
        @CsvSource({"-1,0", "-2,0", "-7,0"})
        @DisplayName("킹과 퀸 왼쪽 이동 테스트")
        void left(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.LEFT);
        }

        @ParameterizedTest
        @CsvSource({"1,0", "2,0", "7,0"})
        @DisplayName("킹과 퀸 오른쪽 이동 테스트")
        void right(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"0,1", "0,2", "0,7"})
        @DisplayName("킹과 퀸 윗 방향 이동 테스트")
        void up(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.UP);
        }

        @ParameterizedTest
        @CsvSource({"0,-1", "0,-2", "0,-7"})
        @DisplayName("킹과 퀸 아랫 방향 이동 테스트")
        void down(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.DOWN);
        }

        @ParameterizedTest
        @CsvSource({"-1,1", "-2,2", "-7,7"})
        @DisplayName("킹과 퀸 왼쪽 윗 방향 이동 테스트")
        void up_left(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.UP_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "7,7"})
        @DisplayName("킹과 퀸 오른쪽 윗 방향 이동 테스트")
        void up_right(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.UP_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"1,-1", "2,-2", "7,-7"})
        @DisplayName("킹과 퀸 오른쪽 아랫 방향 이동 테스트")
        void down_right(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.DOWN_RIGHT);
        }

        @ParameterizedTest
        @CsvSource({"-1,-1", "-2,-2", "-7,-7"})
        @DisplayName("킹과 퀸 왼쪽 아랫 방향 이동 테스트")
        void down_left(int fileDirection, int rankDirection) {
            assertThat(findKingAndQueenDirection(fileDirection, rankDirection)).isEqualTo(Direction.DOWN_LEFT);
        }

        @ParameterizedTest
        @CsvSource({"-1,2", "3,2", "1,2"})
        @DisplayName("킹과 퀸 이동 실패 케이스")
        void fail(int fileDirection, int rankDirection) {
            assertThatThrownBy(() -> findKingAndQueenDirection(fileDirection, rankDirection))
                    .isInstanceOf(WrongDirectionException.class);
        }
    }
}