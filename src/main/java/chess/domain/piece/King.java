package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class King extends Piece {

    public King(TeamColor teamColor, Position position) {
        super(new PieceDetails("king", teamColor, Score.from(0), false),
            new AvailableDirections(PieceDirection.aroundDirections(),
                PieceDirection.aroundDirections()),
            position);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
