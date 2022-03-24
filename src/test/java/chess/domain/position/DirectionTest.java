package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    @DisplayName("source와 target이 수직직진 관계이면 VERTICAL을 반환한다")
    void verticalRelation_returnVerticalDirection() {
        assertThat(Direction.calculate(Position.of("a1"), Position.of("a2")))
                .isEqualTo(Direction.VERTICAL);
    }

    @Test
    @DisplayName("source와 target이 수평직진 관계이면 HORIZONTAL을 반환한다")
    void horizontalRelation_returnHorizontalDirection() {
        assertThat(Direction.calculate(Position.of("a1"), Position.of("c1")))
                .isEqualTo(Direction.HORIZONTAL);
    }

    @Test
    @DisplayName("source와 target이 대각선 관계이면 DIAGONAL을 반환한다")
    void diagonalRelation_returnDiagonalDirection() {
        assertThat(Direction.calculate(Position.of("a1"), Position.of("b2")))
                .isEqualTo(Direction.DIAGONAL);
    }

    @Test
    @DisplayName("source와 target이 아무 관계도 아니면 IGNORE을 반환한다")
    void noRelation_returnIgnoreDirection() {
        assertThat(Direction.calculate(Position.of("a1"), Position.of("c2")))
                .isEqualTo(Direction.IGNORE);
    }
}
