package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class Queen extends Piece {

    public Queen(TeamColor teamColor, Position position) {
        super(new PieceDetails("queen", teamColor, Score.from(9), true),
            new AvailableDirections(PieceDirection.aroundDirections(),
                PieceDirection.aroundDirections()),
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
