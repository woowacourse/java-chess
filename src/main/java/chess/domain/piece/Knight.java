package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.TeamColor;

public class Knight extends Piece {

    public Knight(TeamColor teamColor, Position position) {
        super("n", teamColor, Direction.knightDirections(), Direction.knightDirections(), false, position);
    }

}
