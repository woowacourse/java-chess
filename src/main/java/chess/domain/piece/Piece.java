package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Board;
import chess.domain.Navigator;
import chess.domain.Position;

public abstract class Piece {
    protected final Aliance aliance;

    public Piece(Aliance aliance) {
        this.aliance = aliance;
    }

    public abstract void checkPossibleMove(Board board, Position startPosition, Navigator navigator);

    public boolean isDifferentTeam(Aliance thisTurn) {
        return aliance != thisTurn;
    }

}
