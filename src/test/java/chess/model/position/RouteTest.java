package chess.model.position;

import static chess.model.Fixtures.A3;
import static chess.model.Fixtures.A7;
import static chess.model.Fixtures.B4;
import static chess.model.Fixtures.C5;
import static chess.model.Fixtures.D6;
import static chess.model.Fixtures.E7;
import static chess.model.Fixtures.EMPTY_PIECES;
import static chess.model.material.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Pawn;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RouteTest {

    @DisplayName("출발 위치와 도착 위치의 경로를 찾는다")
    @Test
    void findRoute() {
        Route route = Route.of(A3, E7);
        Set<Position> expectedRoute = Set.of(B4, C5, D6);
        assertThat(route.containsAll(expectedRoute)).isTrue();
    }

    @DisplayName("경로가 막혀있으면 false를 반환한다")
    @Test
    void routeIsBlocked() {
        EMPTY_PIECES.put(C5, Pawn.of(BLACK));
        Route route = Route.of(A3, E7);
        assertThat(route.isBlocked(EMPTY_PIECES)).isTrue();
    }

    @DisplayName("경로가 막혀있지 않으면 true를 반환한다")
    @Test
    void routeIsNotBlocked() {
        Route route = Route.of(A3, A7);
        assertThat(route.isBlocked(EMPTY_PIECES)).isFalse();
    }
}
