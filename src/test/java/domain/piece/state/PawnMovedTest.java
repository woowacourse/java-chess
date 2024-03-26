package domain.piece.state;

import static domain.piece.info.Direction.DOWN;
import static domain.piece.info.Direction.DOWN_LEFT;
import static domain.piece.info.Direction.DOWN_RIGHT;
import static domain.piece.info.Direction.UP;
import static domain.piece.info.Direction.UP_LEFT;
import static domain.piece.info.Direction.UP_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnMovedTest {
    @Test
    @DisplayName("폰이 한 번 이동한 후의 이동 가능 방향을 반환한다.")
    void movableDirection() {
        final PawnMoved pawnMoved = new PawnMoved();

        final List<Direction> blackDirections = pawnMoved.movableDirection(Color.BLACK);
        final List<Direction> whiteDirections = pawnMoved.movableDirection(Color.WHITE);

        assertAll(
                () -> assertThat(blackDirections).containsExactly(DOWN, DOWN_RIGHT, DOWN_LEFT),
                () -> assertThat(whiteDirections).containsExactly(UP, UP_RIGHT, UP_LEFT)
        );
    }
}
