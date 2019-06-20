package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team, Type.KNIGHT, new Navigator(
                Arrays.asList(
                        new Way(Direction.DOUBLE_UP_LEFT, 1),
                        new Way(Direction.DOUBLE_UP_RIGHT, 1),
                        new Way(Direction.DOUBLE_LEFT_UP, 1),
                        new Way(Direction.DOUBLE_LEFT_DOWN, 1),
                        new Way(Direction.DOUBLE_RIGHT_UP, 1),
                        new Way(Direction.DOUBLE_RIGHT_DOWN, 1),
                        new Way(Direction.DOUBLE_DOWN_LEFT, 1),
                        new Way(Direction.DOUBLE_DOWN_RIGHT, 1)
                )
        ));
    }
}
