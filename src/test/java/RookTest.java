import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @DisplayName("source position이 target position까지 수직 위 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenRookDirectionIsDiagonal() {
        Rook whiteRook = Rook.createOfWhite();
        boolean movable = whiteRook.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("c", "4"));

        assertThat(movable).isTrue();
    }
}