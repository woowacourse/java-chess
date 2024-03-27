package domain.position;

import static domain.piece.PositionFixture.A1;
import static domain.piece.PositionFixture.A2;
import static domain.piece.PositionFixture.A8;
import static domain.piece.PositionFixture.B1;
import static domain.piece.PositionFixture.C1;
import static domain.piece.PositionFixture.H1;
import static domain.piece.PositionFixture.H8;
import static domain.position.Direction.DOWN;
import static domain.position.Direction.LEFT;
import static domain.position.Direction.LEFT_DOWN;
import static domain.position.Direction.LEFT_UP;
import static domain.position.Direction.RIGHT;
import static domain.position.Direction.RIGHT_DOWN;
import static domain.position.Direction.RIGHT_UP;
import static domain.position.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    @DisplayName("두 위치를 통해 방향을 판단한다.")
    void asDirection() {
        assertAll(() -> {
            assertThat(Direction.asDirection(A1, A2)).isEqualTo(UP);
            assertThat(Direction.asDirection(A2, A1)).isEqualTo(DOWN);
            assertThat(Direction.asDirection(B1, C1)).isEqualTo(RIGHT);
            assertThat(Direction.asDirection(B1, A1)).isEqualTo(LEFT);
            assertThat(Direction.asDirection(A1, H8)).isEqualTo(RIGHT_UP);
            assertThat(Direction.asDirection(A8, H1)).isEqualTo(RIGHT_DOWN);
            assertThat(Direction.asDirection(H1, A8)).isEqualTo(LEFT_UP);
            assertThat(Direction.asDirection(H8, A1)).isEqualTo(LEFT_DOWN);
        });
    }

    @Test
    @DisplayName("모든 방향을 반환한다.")
    void allDirections() {
        assertThat(Direction.allDirections())
                .containsOnly(UP, DOWN, RIGHT, LEFT, RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);
    }
}
