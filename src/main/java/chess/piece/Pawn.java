package chess.piece;

import chess.PieceColor;

public class Pawn extends Piece {

    private static final String emblem = "P";

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }
}
