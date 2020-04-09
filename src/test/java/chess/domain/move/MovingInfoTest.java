package chess.domain.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovingInfoTest {
    @Test
    @DisplayName("MovingInfo 생성")
    void create() {
        Position startPosition = Position.of(Coordinate.of(2), Coordinate.of(1));
        Position targetPosition = Position.of(Coordinate.of(4), Coordinate.of(1));

        assertThat(MovingInfo.of(startPosition, targetPosition)).isInstanceOf(MovingInfo.class);
    }

    @Test
    @DisplayName("getStartPosition 테스트")
    void getStartPosition() {
        Position startPosition = Position.of(Coordinate.of(2), Coordinate.of(1));
        Position targetPosition = Position.of(Coordinate.of(4), Coordinate.of(1));
        MovingInfo movingInfo = MovingInfo.of(startPosition, targetPosition);

        assertThat(movingInfo.getStartPosition()).isEqualTo(startPosition);
    }

    @Test
    @DisplayName("getTargetPosition 생성")
    void getTargetPosition() {
        Position startPosition = Position.of(Coordinate.of(2), Coordinate.of(1));
        Position targetPosition = Position.of(Coordinate.of(4), Coordinate.of(1));
        MovingInfo movingInfo = MovingInfo.of(startPosition, targetPosition);

        assertThat(movingInfo.getTargetPosition()).isEqualTo(targetPosition);
    }
}
