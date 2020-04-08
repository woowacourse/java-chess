package chess.board;

import chess.location.Location;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RouteTest {

    @Test
    @DisplayName("좌표가 비어있지 않을 경우")
    void isNotEmpty() {
        Map<Location, Piece> route = new HashMap<>();
        Location location = new Location(2, 'b');

        route.put(location, new Pawn(Team.WHITE));

        Route route1 = new Route(route, new Location(1, 'b'), new Location(3, 'b'));
        assertThat(route1.isExistPieceIn(location)).isTrue();
    }

    @Test
    @DisplayName("좌표가 비어있는 경우")
    void isNotEmpty2() {
        Map<Location, Piece> route = new HashMap<>();
        Location location = new Location(2, 'b');

        Route route1 = new Route(route, new Location(1, 'b'), new Location(3, 'b'));
        assertThat(route1.isExistPieceIn(location)).isFalse();
    }
}