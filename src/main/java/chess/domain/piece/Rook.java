package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.TeamColor;

public class Rook extends Piece {

    public Rook(TeamColor teamColor, Position position) {
        super("r", teamColor, Direction.straightDirections(), Direction.straightDirections(), true, position);

    }

}
