package chess.piece;

import chess.PieceColor;

public class EmptyPiece extends Piece {
    private static final String emblem = ".";

    public EmptyPiece(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }
}
