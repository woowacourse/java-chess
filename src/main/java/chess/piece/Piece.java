package chess.piece;

import chess.Color;
import chess.board.Position;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    protected final PieceType pieceType;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public abstract boolean isMovable(Position startPoint, Position destination);

    public abstract List<Position> createAllPaths(Position startPoint, Position destination);

    public boolean canJumpOver() {
        return false;
    };

    public boolean isEqualColor(Piece other) {
        return this.color == other.color;
    }

    public boolean isGameStopIfDie(){
        return false;
    };

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
