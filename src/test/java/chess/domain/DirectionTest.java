package chess.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {"LEFT,0", "RIGHT,0", "LEFT_UP,-1",
            "LEFT_DOWN,1", "RIGHT_UP,1", "RIGHT_DOWN,-1",
            "KNIGHT_LEFT_UP,-0.5", "KNIGHT_LEFT_DOWN,0.5",
            "KNIGHT_RIGHT_UP,0.5", "KNIGHT_RIGHT_DOWN,-0.5",
            "KNIGHT_UP_LEFT,-2", "KNIGHT_UP_RIGHT,2",
            "KNIGHT_DOWN_LEFT,2", "KNIGHT_DOWN_RIGHT,-2"})
    @DisplayName("방향의 기울기를 구한다.")
    void calculateGradiant(Direction direction, double expectedGradiant) {
        double actualGradiant = direction.calculateGradiant();

        assertThat(actualGradiant).isEqualTo(expectedGradiant);
    }

    @Test
    @DisplayName("방향의 기울기가 무한대가 되는 경우에도 기울기를 구할 수 있다.")
    void calculateInfiniteGradiant() {
        double upGradiant = Direction.UP.calculateGradiant();
        double downGradiant = Direction.DOWN.calculateGradiant();

        assertAll(
                () -> assertThat(upGradiant).isInfinite(),
                () -> assertThat(downGradiant).isInfinite()
        );
    }
}
