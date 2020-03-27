package chess.domain.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovingInfoTest {
    @Test
    @DisplayName("MovingInfo 생성")
    void create() {
        Position startPosition = Position.of(2, 1);
        Position targetPosition = Position.of(4, 1);

        assertThat(new MovingInfo(startPosition, targetPosition)).isInstanceOf(MovingInfo.class);
    }

    @Test
    @DisplayName("getStartPosition 테스트")
    void getStartPosition() {
        Position startPosition = Position.of(2, 1);
        Position targetPosition = Position.of(4, 1);
        MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

        assertThat(movingInfo.getStartPosition()).isEqualTo(startPosition);
    }

    @Test
    @DisplayName("getTargetPosition 생성")
    void getTargetPosition() {
        Position startPosition = Position.of(2, 1);
        Position targetPosition = Position.of(4, 1);
        MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

        assertThat(movingInfo.getTargetPosition()).isEqualTo(targetPosition);
    }
}
