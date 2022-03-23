package chess.piece;

import chess.PieceColor;

public class Knight extends Piece {

    private static final String emblem = "N";

    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }
}
