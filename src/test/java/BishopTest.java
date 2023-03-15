import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("source position이 target position까지 대각선 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenBishopDirectionIsDiagonal() {
        Bishop whiteBishop = Bishop.createOfWhile();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("e", "4"));

        assertThat(movable).isTrue();
    }
}