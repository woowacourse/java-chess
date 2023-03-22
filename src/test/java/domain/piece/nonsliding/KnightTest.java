package domain.piece.nonsliding;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KnightTest {

    @Test
    @DisplayName("나이트는 기본 상태를 가진다")
    void propertyTest() {
        Knight knight = new Knight(Color.WHITE);

        assertThat(knight.canJump()).isTrue();
        assertThat(knight.isPawn()).isFalse();
        assertThat(knight.getPoint()).isEqualTo(2.5);
        assertThat(knight.isKing()).isFalse();
        assertThat(knight.hasSameColorWith(Color.WHITE)).isTrue();
    }

    @ParameterizedTest(name = "(4, 4)에서 ({0}, {1})로의 이동이 가능하다")
    @CsvSource(value = {"6:5", "6:3", "2:5", "2:3", "5:6", "3:6", "3:2", "5:2"}, delimiter = ':')
    void isReachableByRule(int row, int col) {
        Coordinate startCoordinate = new Coordinate(4, 4);
        Coordinate endCoordinate = new Coordinate(row, col);
        Knight knight = new Knight(Color.WHITE);

        assertThat(knight.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @ParameterizedTest(name = "(4, 4)에서 ({0}, {1})으로의 직선 이동은 불가능하다")
    @CsvSource(value = {"3:4", "5:4", "4:3", "4:2"}, delimiter = ':')
    void isNotReachableByRuleStraight(int row, int col) {
        Coordinate startCoordinate = new Coordinate(4, 4);
        Coordinate endCoordinate = new Coordinate(row, col);
        Knight knight = new Knight(Color.WHITE);

        assertThat(knight.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }

    @ParameterizedTest(name = "(4, 4)에서 ({0}, {1})으로의 대각선 이동은 불가능하다")
    @CsvSource(value = {"4:5", "4:3", "3:3", "3:5"}, delimiter = ':')
    void isNotReachableByRuleDiagonal(int row, int col) {
        Coordinate startCoordinate = new Coordinate(4, 4);
        Coordinate endCoordinate = new Coordinate(row, col);
        Knight knight = new Knight(Color.WHITE);

        assertThat(knight.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }
}
