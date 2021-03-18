package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.TeamColor;

public class Bishop extends Piece {

    public Bishop(TeamColor teamColor, Position position) {
        super("b", teamColor, Direction.diagonalDirections(), Direction.diagonalDirections(), true, position);
    }
}
