package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class Rook extends Piece {

    public Rook(TeamColor teamColor, Position position) {
        super(new PieceDetails("rook", teamColor, Score.from(5), true),
            new AvailableDirections(PieceDirection.straightDirections(),
                PieceDirection.straightDirections()),
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
