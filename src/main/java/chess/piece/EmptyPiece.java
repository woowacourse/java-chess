package chess.piece;

import chess.PieceColor;
import chess.Position;

public class EmptyPiece extends Piece {
    private static final String emblem = ".";

    public EmptyPiece(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
