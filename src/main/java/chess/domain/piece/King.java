package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;

public class King extends Piece {

    private static final String NAME = "k";

    public King(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean movable(Position currentPosition, Position targetPosition) {
        return currentPosition.isAroundPosition(targetPosition);
    }
}
