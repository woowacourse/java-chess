package chess;

import chess.domain.Direction;
import chess.domain.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.Position.POSITION_CACHE;
import static chess.domain.Position.findPosition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b1", "c1", "d1", "e1", "h1", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"})
    @DisplayName("가로 세로 위치 정보를 문자열로 입력할 때 해당 위치를 가진 Position 객체가 반환된다.")
    void shouldSucceedFindPosition(String position) {

        assertThat(findPosition(position)).isInstanceOf(Position.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "a10", "y1", "z2", "aa2", "b33"})
    @DisplayName("해당 Position 객체를 찾기 위해 잘못된 체스 좌표를 입력했을 때 예외 발생")
    void shouldFailFindPosition(String position) {

        Assertions.assertThatThrownBy(() -> Position.findPosition(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 Position은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("현재 포지션과 동쪽 방향 객체를 입력하면 동쪽으로 한 칸 이동한 포지션이 반환된다.")
    void shouldSucceedToReturnEastPosition() {

        Position position = Position.findPosition("f2");
        Direction northDirection = Direction.EAST;
        Position expectedPosition = Position.findPosition("g2");

        assertThat(position.getMovingPosition(northDirection)).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("현재 포지션과 서쪽 방향 객체를 입력하면 서쪽으로 한 칸 이동한 포지션이 반환된다.")
    void shouldSucceedToReturnWestPosition() {

        Position position = Position.findPosition("f2");
        Direction northDirection = Direction.WEST;
        Position expectedPosition = Position.findPosition("e2");

        assertThat(position.getMovingPosition(northDirection)).isEqualTo(expectedPosition);
    }


    @Test
    @DisplayName("현재 포지션과 남쪽 방향 객체를 입력하면 남쪽으로 한 칸 이동한 포지션이 반환된다.")
    void shouldSucceedToReturnSouthPosition() {

        Position position = Position.findPosition("f2");
        Direction northDirection = Direction.SOUTH;
        Position expectedPosition = Position.findPosition("f1");

        assertThat(position.getMovingPosition(northDirection)).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("현재 포지션과 북쪽 방향 객체를 입력하면 북쪽으로 한 칸 이동한 포지션이 반환된다.")
    void shouldSucceedToReturnNorthPosition() {

        Position position = Position.findPosition("f2");
        Direction northDirection = Direction.NORTH;
        Position expectedPosition = Position.findPosition("f3");

        assertThat(position.getMovingPosition(northDirection)).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("현재 포지션에서 이동한 위치가 체스판 범위를 넘어설 경우 예외가 발생한다.")
    void shouldFailToReturnMovingPosition() {
        Position position = Position.findPosition("a1");

        assertThatThrownBy(() -> position.getMovingPosition(Direction.WEST))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 포지션은 체스판 범위 밖입니다.");
    }

    @Test
    @DisplayName("두 포지션 객체가 입력될 때 column 간 거리를 반환한다.")
    void shouldSucceedGettingColumnDistance() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f6");
        int expectedColumnDistance = 2;

        assertThat(sourcePosition.getColumnDistanceFromTargetToSource(targetPosition)).isEqualTo(expectedColumnDistance);
    }

    @Test
    @DisplayName("두 포지션 객체가 입력될 때 rank 간 거리를 반환한다.")
    void shouldSucceedGettingRankDistance() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f6");
        int expectedRankDistance = 3;

        assertThat(sourcePosition.getRankDistanceFromTargetToSource(targetPosition)).isEqualTo(expectedRankDistance);
    }

    @Test
    @DisplayName("포지션 객체가 화이트폰의 초기 Rank위치(2)에 존재할 경우 true를 반환")
    void shouldSucceedCheckWhitePawnIn2ndRankTrue() {
        Position position1 = Position.findPosition("a2");
        Position position2 = Position.findPosition("d2");
        Position position3 = Position.findPosition("h2");

        assertAll(
                () -> assertThat(position1.isInWhitePawnInitRank()).isTrue(),
                () -> assertThat(position2.isInWhitePawnInitRank()).isTrue(),
                () -> assertThat(position3.isInWhitePawnInitRank()).isTrue()
        );

    }

    @Test
    @DisplayName("포지션 객체가 화이트폰의 초기 Rank위치(2)에 존재하지 않을 경우 false를 반환")
    void shouldSucceedCheckWhitePawnIn2ndRankFalse() {
        Position position1 = Position.findPosition("a3");
        Position position2 = Position.findPosition("d5");
        Position position3 = Position.findPosition("h7");

        assertAll(
                () -> assertThat(position1.isInWhitePawnInitRank()).isFalse(),
                () -> assertThat(position2.isInWhitePawnInitRank()).isFalse(),
                () -> assertThat(position3.isInWhitePawnInitRank()).isFalse()
        );
    }

    @Test
    @DisplayName("포지션 객체가 블랙폰의 초기 Rank위치(7)에 존재할 경우 true를 반환")
    void shouldSucceedCheckBlackPawnIn2ndRankTrue() {
        Position position1 = Position.findPosition("a7");
        Position position2 = Position.findPosition("d7");
        Position position3 = Position.findPosition("h7");

        System.out.println(POSITION_CACHE);
        assertAll(
                () -> assertThat(position1.isInBlackPawnInitRank()).isTrue(),
                () -> assertThat(position2.isInBlackPawnInitRank()).isTrue(),
                () -> assertThat(position3.isInBlackPawnInitRank()).isTrue()
        );
    }

    @Test
    @DisplayName("포지션 객체가 블랙폰의 초기 Rank위치(7)에 존재하지 않을 경우 false를 반환")
    void shouldSucceedCheckBlackPawnIn2ndRankFalse() {
        Position position1 = Position.findPosition("a2");
        Position position2 = Position.findPosition("d4");
        Position position3 = Position.findPosition("h6");

        assertAll(
                () -> assertThat(position1.isInBlackPawnInitRank()).isFalse(),
                () -> assertThat(position2.isInBlackPawnInitRank()).isFalse(),
                () -> assertThat(position3.isInBlackPawnInitRank()).isFalse()
        );
    }

    //    @ParameterizedTest()
//    @ValueSource(strings = {"i1", "a0", "b9", "b0"," ", "asd", "123", "", "ac1", "b78"})
//    @DisplayName("move command의 source위치와 target위치가 체스판 좌표 형식에 맞지 않으면 예외처리한다.")
//    void shouldFailMismatchPositionRegex(String input) {
//        assertThatThrownBy(() -> Position.validatePositionRegex(input))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("[ERROR] 이동 방향이 체스판 형식에 맞지 않습니다.");
//    }
//
//    @ParameterizedTest()
//    @ValueSource(strings = {"h1", "a8", "b5", "c4", "a1", "h8", "a8"})
//    @DisplayName("move command의 source위치와 target위치가 체스판 좌표 형식에 맞으면 성공한다.")
//    void shouldSuccessMatchPositionRegex(String input) {
//        Assertions.assertDoesNotThrow(() -> Position.validatePositionRegex(input));
//    }
//
//
//    @ParameterizedTest
//    @CsvSource(value = {"b1, 2, 1", "c8, 3, 8", "a8, 1, 8", "h1, 8, 1", "h8, 8, 8", "c4, 3, 4", "a1, 1, 1"})
//    @DisplayName("체스판 형식에 맞는 위치 정보가 올바르게 숫자로 변환된다.")
//    void shouldSuccessConvertCommandToNumber(String input, int x, int y) {
//        assertThat(Position.convertCommand(input)).isEqualTo(List.of(x, y));
//
//    }
}
