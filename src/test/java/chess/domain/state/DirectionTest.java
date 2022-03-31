package chess.domain.state;

import chess.domain.location.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("허용되지 않은 방향이 입력되면 예외발생")
    void noDirectionException() {
        Assertions.assertThatThrownBy(() -> Direction.of(1, 3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("체스기물에게 허용된방향이 입력되면 Direction 반환")
    void chessPieceAllowedDirection() {
        Direction direction = Direction.of(1, 1);
        Assertions.assertThat(direction).isEqualTo(Direction.UR);
    }
}