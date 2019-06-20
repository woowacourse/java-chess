package chess.domain.piece;

import chess.domain.direction.Route;
import chess.domain.direction.core.Direction;
import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class Pawn {
    private Team team;
    private Navigator navigator;
    private Type type;

    public Pawn() {
        this(Team.WHITE);
    }

    public Pawn(Team team) {
        navigator = new Navigator(
                Arrays.asList(
                      new Way(Direction.UP, 2),
                      new Way(Direction.UP, 1),
                      new Way(Direction.UP_LEFT, 1),
                      new Way(Direction.UP_RIGHT, 1)
                )
        );
        this.team = team;
    }

    Route getRoute(Square source, Square target) {
        Route route = navigator.getWay(source, target);
        if (route == null) {
            throw new IllegalArgumentException("갈 수 없습니다.");
        }
        return route;
    }

}
