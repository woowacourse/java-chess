package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public Direction findDirectionToMove(Position start, Position end) {
        return Direction.findDirectionByGap(start, end);
    }

    public abstract boolean isMovable(Position start, Position end, Color colorOfDestination);

    protected abstract void checkMovableDirection(Direction direction);

    public Color getColor() {
        return color;
    }

    //remove
    public PieceType getPieceType() {
        return pieceType;
    }
}
