package chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionTest {
    XPosition xPosition;
    YPosition yPosition;
    Position position;

    @BeforeEach
    void setUp() {
        xPosition = XPosition.A;
        yPosition = YPosition.One;

        position = new Position(xPosition, yPosition);
    }

    @DisplayName("좌표 조회")
    @Test
    void getPosition_StringInt() {
        XPosition xPositionResult = position.getXPosition();
        YPosition yPositionResult = position.getYPosition();

        assertThat(xPositionResult).isEqualTo(xPosition);
        assertThat(yPositionResult).isEqualTo(yPosition);
    }

    @DisplayName("좌표 동일성 비교")
    @Test
    void isEqualPosition_boolean() {
        Position anotherPosition = new Position(xPosition, yPosition);

        assertEquals(position, anotherPosition);
    }
}