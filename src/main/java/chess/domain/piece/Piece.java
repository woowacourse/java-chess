package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

public abstract class Piece {
    private final String team;

    public Piece(final String team) {
        this.team = team;
    }

    abstract boolean isMovable(final Position current, final Position destination, final ChessBoard chessBoard);

    public final String getTeam() {
        return team;
    }
}
