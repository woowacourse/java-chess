package chess.domain.piece;

import chess.domain.board.PlayerType;
import chess.domain.board.Point;

import java.util.List;

public abstract class Piece {
    protected PlayerType playerType;

    public Piece(PlayerType playerType) {
        this.playerType = playerType;
    }

    public boolean isSamePlayerType(Piece piece) {
        return this.playerType.equals(piece.playerType);
    }

    public boolean isSamePlayerType(PlayerType playerType) {
        return this.playerType.equals(playerType);
    }

    public boolean isWhite() {
        return playerType == PlayerType.WHITE;
    }

    public abstract boolean isMovable(Point prev, Point next);

    public abstract String pieceToString();
}
