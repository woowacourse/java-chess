package chess.piece;

import chess.PieceColor;
import chess.Position;

public class King extends Piece {
    private static final String emblem = "K";

    public King(PieceColor pieceColor) {
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
