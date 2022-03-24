package chess.piece;

import chess.PieceColor;
import chess.Position;

public class Queen extends Piece {

    private static final String emblem = "Q";

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isAllDirectional(target);
    }
}
