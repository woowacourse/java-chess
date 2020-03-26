package chess.domain;

import chess.domain.Piece.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("#calculateDistance() : should return Distance between Positions")
    void calculateDistance() {
        //given
        int fromX = 1;
        int fromY = 1;
        Position from = Position.of(fromX, fromY);
        int toX = 2;
        int toY = 2;
        Position to = Position.of(toX, toY);
        //when
        Distance distance = from.calculateDistance(to);
        //then
        assertThat(distance).isEqualTo(new Distance(toX - fromX, toY - fromY));
    }
}
