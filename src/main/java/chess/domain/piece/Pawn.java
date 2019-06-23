package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Route;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece {
    private boolean isFirstMoved = false;

    public Pawn(Team team) {
        super(team, Type.PAWN);
        navigator = generateNavigator(team);
    }

    private Navigator generateNavigator(Team team) {
        return team == Team.WHITE ? generateWhiteNavigator() : generateBlackNavigator();
    }

    private Navigator generateWhiteNavigator() {
        return new Navigator(new ArrayList<>(
                Arrays.asList(
                        new Way(Direction.UP, MoveStrategy.ONLY_EMPTY, 2),
                        new Way(Direction.UP, MoveStrategy.ONLY_EMPTY, 1),
                        new Way(Direction.UP_LEFT, MoveStrategy.ONLY_ENEMY, 1),
                        new Way(Direction.UP_RIGHT, MoveStrategy.ONLY_ENEMY, 1)
                )
        ));
    }

    private Navigator generateBlackNavigator() {
        return new Navigator(new ArrayList<>(
                Arrays.asList(
                        new Way(Direction.DOWN, MoveStrategy.ONLY_EMPTY, 2),
                        new Way(Direction.DOWN, MoveStrategy.ONLY_EMPTY, 1),
                        new Way(Direction.DOWN_LEFT, MoveStrategy.ONLY_ENEMY, 1),
                        new Way(Direction.DOWN_RIGHT, MoveStrategy.ONLY_ENEMY, 1)
                )
        ));
    }

    @Override
    public Route getRoute(Square source, Square target) {
        Route route = super.getRoute(source, target);
        if (!isFirstMoved) {
            navigator.firstRemove();
            isFirstMoved = true;
        }
        return route;
    }
}
