package chess.piece;

import chess.PieceColor;
import chess.Position;

public class Knight extends Piece {

    private static final String emblem = "N";

    public Knight(PieceColor pieceColor) {
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
