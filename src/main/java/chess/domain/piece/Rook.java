package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;

public class Rook extends Piece {

    private static final String NAME = "r";

    public Rook(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean movable(Position currentPosition, Position targetPosition) {
        return currentPosition.isStraight(targetPosition);
    }
}
