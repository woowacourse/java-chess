package chess.piece;

import chess.PieceColor;

public class King extends Piece {
    private static final String emblem = "K";

    public King(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }
}
