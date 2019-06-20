package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Position;

public abstract class Piece {
    protected final Aliance aliance;

    public Piece(Aliance aliance) {
        this.aliance = aliance;
    }

    public abstract Position isPossibleMove();

    public boolean isDifferentTeam(Aliance thisTurn) {
        return aliance != thisTurn;
    }
}
