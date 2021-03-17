package chess.domain.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    Position position;

    @BeforeEach
    void setUp() {
        position = Position.of('a', 1);
    }

    @DisplayName("좌표 조회")
    @Test
    void getPosition_StringInt() {
        XPosition xPositionResult = position.getXPosition();
        YPosition yPositionResult = position.getYPosition();

        assertThat(xPositionResult).isEqualTo(XPosition.A);
        assertThat(yPositionResult).isEqualTo(YPosition.One);
    }

    @DisplayName("좌표 동일성 비교")
    @Test
    void isEqualPosition_boolean() {
        Position anotherPosition = Position.of('a', 1);

        assertEquals(position, anotherPosition);
    }
}