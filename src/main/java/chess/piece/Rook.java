package chess.piece;

import chess.PieceColor;

public class Rook extends Piece {

    private static final String emblem = "R";

    public Rook(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }
}
