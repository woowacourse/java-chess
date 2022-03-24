package chess.piece;

import chess.PieceColor;
import chess.Position;

public class Rook extends Piece {

    private static final String emblem = "R";

    public Rook(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isCross(target);
    }
}
