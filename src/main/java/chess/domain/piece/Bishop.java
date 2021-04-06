package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class Bishop extends Piece {

    public Bishop(TeamColor teamColor, Position position) {
        super(new PieceDetails("bishop", teamColor, Score.from(3), true),
            new AvailableDirections(PieceDirection.diagonalDirections(),
                PieceDirection.diagonalDirections()),
            position);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
