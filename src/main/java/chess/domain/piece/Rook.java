package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class Rook extends Piece {

    public Rook(Team team) {
        super(team, Type.ROOK, new Navigator(
                Arrays.asList(
                        new Way(Direction.UP, MoveStrategy.BOTH),
                        new Way(Direction.DOWN, MoveStrategy.BOTH),
                        new Way(Direction.LEFT, MoveStrategy.BOTH),
                        new Way(Direction.RIGHT, MoveStrategy.BOTH)
                )
        ));
    }
}
