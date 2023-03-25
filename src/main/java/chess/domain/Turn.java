package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;

public class Turn {

    private final Camp turnCamp;

    public Turn(final Camp camp) {
        this.turnCamp = camp;
    }

    public Turn convert(final Turn turn) {
        if (turn.turnCamp == Camp.BLACK) {
            return new Turn(Camp.WHITE);
        }

        return new Turn(Camp.BLACK);
    }

    public boolean isMyTurn(final Piece fromPiece) {
        return fromPiece.isMyCamp(turnCamp);
    }
}
