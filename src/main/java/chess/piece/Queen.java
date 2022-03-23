package chess.piece;

import chess.PieceColor;

public class Queen extends Piece {

    private static final String emblem = "Q";

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }
}
