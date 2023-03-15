package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    @DisplayName("위로 한 칸 이동할 수 있다")
    void isReachableByRuleUp() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(2, 0);
        Pawn pawn = new Pawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("아래로 한 칸 이동할 수 있다")
    void isReachableByRuleDown() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(0, 0);
        Pawn pawn = new Pawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }
}
