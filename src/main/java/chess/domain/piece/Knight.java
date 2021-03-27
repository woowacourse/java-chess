package chess.domain.piece;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class Knight extends Piece {

    public Knight(TeamColor teamColor, Position position) {
        super(new PieceDetails("knight", teamColor, Score.from(2.5), false),
            new AvailableDirections(PieceDirection.knightDirections(),
                PieceDirection.knightDirections()),
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
