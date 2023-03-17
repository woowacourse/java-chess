package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoveTest {

    @DisplayName("변화가 없는 움직임은 생성할 수 없다")
    @Test
    void findRoute_zero_throws() {
        assertThatThrownBy(() -> new Move(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직임이 없습니다.");
    }

    @DisplayName("대각선인지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"3,3", "-1,1", "-3,-3", "1,-1"})
    void isDiagonal(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isDiagonal()).isTrue();
    }

    @DisplayName("대각선이 아닌지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"2,1", "0,1", "1,0"})
    void isNotDiagonal(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isDiagonal()).isFalse();
    }

    @DisplayName("수평/수직선인지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"3,0", "0,1", "-1,0", "0,-3"})
    void isStraight(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isStraight()).isTrue();
    }

    @DisplayName("수평/수직선이 아닌지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"2,1", "1,1"})
    void isNotStraight(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isStraight()).isFalse();
    }

    @DisplayName("단위 대각선인지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "-1,1", "-1,-1", "1,-1"})
    void isUnitDiagonal(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isUnitDiagonal()).isTrue();
    }

    @DisplayName("단위 대각선이 아닌지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"2,1", "0,1", "1,0", "2,2"})
    void isNotUnitDiagonal(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isUnitDiagonal()).isFalse();
    }

    @DisplayName("단위 수평/수직선인지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"1,0", "0,1", "-1,0", "0,-1"})
    void isUnitStraight(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isUnitStraight()).isTrue();
    }

    @DisplayName("단위 수평/수직선이 아닌지 판단한다")
    @ParameterizedTest
    @CsvSource(value = {"2,1", "1,1", "2,0"})
    void isNotUnitStraight(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isUnitStraight()).isFalse();
    }

    @DisplayName("변화량 곱이 올바른지 확인한다")
    @ParameterizedTest
    @CsvSource(value = {"2,1,2", "1,2,2", "2,-1,2", "-2,-2,4"})
    void isRightProductOfChange(int deltaFile, int deltaRank, int productOfChange) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isRightProductOfChange(productOfChange)).isTrue();
    }

    @DisplayName("변화량 곱이 올바르지 않은지 확인한다")
    @Test
    void isNotRightProductOfChange() {
        Move move = new Move(2, -1);

        assertThat(move.isRightProductOfChange(-2)).isFalse();
    }

    @DisplayName("경로를 탐색한다(대각선)")
    @Test
    void findDiagonalRoute() {
        Move move = new Move(4, -4);

        List<Move> routes = move.findRoute();

        assertThat(routes).containsExactlyInAnyOrder(
                new Move(1, -1),
                new Move(2, -2),
                new Move(3, -3)
        );
    }

    @DisplayName("경로를 탐색한다(수직/수평선)")
    @Test
    void findStraightRoute() {
        Move move = new Move(-4, 0);

        List<Move> routes = move.findRoute();

        assertThat(routes).containsExactlyInAnyOrder(
                new Move(-1, 0),
                new Move(-2, 0),
                new Move(-3, 0)
        );
    }

    @DisplayName("수직/수평/대각선이 아닌 경우 경로 탐색시 빈 리스트를 반환한다")
    @Test
    void findRoute_notStraightAndDiagonal_empty() {
        Move move = new Move(2, 1);

        List<Move> routes = move.findRoute();

        assertThat(routes).isEmpty();
    }

    @DisplayName("위로 수직/수평선 1번인지 확인한다.")
    @Test
    void isOneWayUnitStraight_up() {
        Move move = new Move(0, 1);

        assertThat(move.isOneWayUnitStraight(true))
                .isTrue();
    }

    @DisplayName("위로 수직/수평선 1번이 아닌지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,-1", "0,2", "1,1"})
    void isOneWayUnitStraight_up_false(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isOneWayUnitStraight(true))
                .isFalse();
    }

    @DisplayName("아래로 수직/수평선 1번인지 확인한다.")
    @Test
    void isOneWayUnitStraight_down() {
        Move move = new Move(0, -1);

        assertThat(move.isOneWayUnitStraight(false))
                .isTrue();
    }

    @DisplayName("아래 수직/수평선 1번이 아닌지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,1", "0,-2", "1,-1"})
    void isOneWayUnitStraight_down_false(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isOneWayUnitStraight(false))
                .isFalse();
    }

    @DisplayName("위로 대각선 1번인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "-1,1"})
    void isOneWayUnitDiagonal_up(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isOneWayUnitDiagonal(true))
                .isTrue();
    }

    @DisplayName("위로 대각선 1번이 아닌지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"2,2", "0,1", "1,-1"})
    void isOneWayUnitDiagonal_up_false(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isOneWayUnitDiagonal(true))
                .isFalse();
    }

    @DisplayName("위로 수평/수직선 n번 이하인지 확인한다.")
    @Test
    void isOneWayStraightUnderSize_up() {
        Move move = new Move(0, 3);

        assertThat(move.isOneWayStraightUnderSize(true, 3))
                .isTrue();
    }

    @DisplayName("위로 수평/수직선 n번 이하가 아닌지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"3,3", "1,-1", "0,-1"})
    void isOneWayStraightUnderSize_up_false(int deltaFile, int deltaRank) {
        Move move = new Move(deltaFile, deltaRank);

        assertThat(move.isOneWayStraightUnderSize(true, 2))
                .isFalse();
    }
}
