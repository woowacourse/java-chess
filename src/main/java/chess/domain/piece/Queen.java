package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team, Type.QUEEN, new Navigator(
                Arrays.asList(
                        new Way(Direction.UP, MoveStrategy.BOTH),
                        new Way(Direction.DOWN, MoveStrategy.BOTH),
                        new Way(Direction.LEFT, MoveStrategy.BOTH),
                        new Way(Direction.RIGHT, MoveStrategy.BOTH),
                        new Way(Direction.UP_LEFT, MoveStrategy.BOTH),
                        new Way(Direction.UP_RIGHT, MoveStrategy.BOTH),
                        new Way(Direction.DOWN_LEFT, MoveStrategy.BOTH),
                        new Way(Direction.DOWN_RIGHT, MoveStrategy.BOTH)
                )
        ));
    }
}
