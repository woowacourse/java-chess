package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team, Type.BISHOP, new Navigator(
                Arrays.asList(
                        new Way(Direction.UP_LEFT),
                        new Way(Direction.UP_RIGHT),
                        new Way(Direction.DOWN_LEFT),
                        new Way(Direction.DOWN_RIGHT)
                )
        ));
    }

}
