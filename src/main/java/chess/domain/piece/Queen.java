package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;

public class Queen extends Piece {

    private static final String NAME = "q";

    public Queen(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean movable(Position currentPosition, Position targetPosition) {
        return currentPosition.isDiagonal(targetPosition)
                || currentPosition.isStraight(targetPosition);
    }
}
