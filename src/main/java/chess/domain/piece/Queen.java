package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.TeamColor;

public class Queen extends Piece {

    public Queen(TeamColor teamColor, Position position) {
        super("q", teamColor, Direction.aroundDirections(), Direction.aroundDirections(), true, position);
    }
}
