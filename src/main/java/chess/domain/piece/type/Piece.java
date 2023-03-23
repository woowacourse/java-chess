package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.List;

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

    public abstract void checkMovable(Position start, Position end, Color destinationColor);

    protected abstract void checkMovableDirection(Direction direction);
    public abstract List<Position> findRoute(Position start, Position end);

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    //

}
