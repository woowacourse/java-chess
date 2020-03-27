package chess.domain.route;

import chess.domain.Team;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RouteFinderTest {

    @Test
    void findRoute() {
        Route foundRoute = RouteFinder.findRoute(Position.of("a2"), Position.of("a3"), Team.WHITE, PieceType.PAWN);

        assertThat(foundRoute).isEqualTo(Route.emptyRoute());
    }
}