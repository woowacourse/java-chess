package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

public abstract class Piece {
    protected PlayerType playerType;

    public Piece(PlayerType playerType) {
        this.playerType = playerType;
    }

    public abstract boolean isMovable(Point prev, Point next);

    public boolean isSamePlayerType(Piece piece) {
        return this.playerType.equals(piece.playerType);
    }

}
