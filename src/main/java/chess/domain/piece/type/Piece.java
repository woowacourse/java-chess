package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.List;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract boolean isMovable(Position start, Position end, Color destinationColor);

    public abstract double getScore(List<Piece> piecesInSameColumn);

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

}
