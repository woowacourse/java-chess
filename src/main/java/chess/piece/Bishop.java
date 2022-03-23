package chess.piece;

import chess.PieceColor;

public class Bishop extends Piece {

    public static final String emblem = "B";

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }

}
