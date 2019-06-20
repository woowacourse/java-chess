package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class King extends Piece {

    public King(Team team) {
        super(team, Type.KING, new Navigator(
                Arrays.asList(
                        new Way(Direction.UP, 1),
                        new Way(Direction.DOWN, 1),
                        new Way(Direction.LEFT, 1),
                        new Way(Direction.RIGHT, 1),
                        new Way(Direction.UP_LEFT, 1),
                        new Way(Direction.UP_RIGHT, 1),
                        new Way(Direction.DOWN_LEFT, 1),
                        new Way(Direction.DOWN_RIGHT, 1)
                )
        ));
    }
}
