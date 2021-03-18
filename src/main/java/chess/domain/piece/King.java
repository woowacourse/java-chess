package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.TeamColor;

public class King extends Piece {

    public King(TeamColor teamColor, Position position) {
        super("k", teamColor, Direction.aroundDirections(), Direction.aroundDirections(), false, position);
    }
}
