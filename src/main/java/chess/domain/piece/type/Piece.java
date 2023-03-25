package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

public abstract class Piece {

    private final PieceType pieceType;
    protected final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract void checkMovable(Position start, Position end, Color destinationColor);

    protected abstract void checkMovableDirection(Direction direction);

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

}
