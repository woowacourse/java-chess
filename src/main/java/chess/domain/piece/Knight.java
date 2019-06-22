package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team, Type.KNIGHT, new Navigator(
                Arrays.asList(
                        new Way(Direction.DOUBLE_UP_LEFT, MoveStrategy.BOTH, 1),
                        new Way(Direction.DOUBLE_UP_RIGHT, MoveStrategy.BOTH, 1),
                        new Way(Direction.DOUBLE_LEFT_UP, MoveStrategy.BOTH, 1),
                        new Way(Direction.DOUBLE_LEFT_DOWN, MoveStrategy.BOTH, 1),
                        new Way(Direction.DOUBLE_RIGHT_UP, MoveStrategy.BOTH, 1),
                        new Way(Direction.DOUBLE_RIGHT_DOWN, MoveStrategy.BOTH, 1),
                        new Way(Direction.DOUBLE_DOWN_LEFT, MoveStrategy.BOTH, 1),
                        new Way(Direction.DOUBLE_DOWN_RIGHT, MoveStrategy.BOTH, 1)
                )
        ));
    }
}
