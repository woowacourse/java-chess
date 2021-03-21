package chess.domain.position;

import static chess.domain.board.File.A;
import static chess.domain.board.File.C;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveRouteTest {
    @DisplayName("유효한 출발위치와 도착위치")
    @Test
    void validMoveRoute() {
        MoveRoute moveRoute = new MoveRoute("a3", "c5");

        assertThat(moveRoute.startPosition()).isEqualTo(new Position(A, THREE));
        assertThat(moveRoute.destination()).isEqualTo(new Position(C, FIVE));
    }

    @DisplayName("유효하지 않은 출발위치와 도착위치")
    @Test
    void invalidMoveRoute() {
        assertThatThrownBy(() -> new MoveRoute("i3", "c9"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}