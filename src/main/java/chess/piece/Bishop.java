package chess.piece;

import chess.PieceColor;
import chess.Position;

public class Bishop extends Piece {

    public static final String emblem = "B";

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isDiagonal(target);
    }

}
