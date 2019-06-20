package chess.domain.piece;

import chess.domain.direction.Navigator;
import chess.domain.direction.Way;
import chess.domain.direction.core.Direction;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;

import java.util.Arrays;

public class Rook extends Piece {

    public Rook(Team team) {
        super(team, Type.ROOK, new Navigator(
                Arrays.asList(
                        new Way(Direction.UP),
                        new Way(Direction.DOWN),
                        new Way(Direction.LEFT),
                        new Way(Direction.RIGHT)
                )
        ));
    }
}
