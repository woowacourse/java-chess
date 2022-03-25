package chess.piece;

import chess.PieceColor;
import chess.Position;

public class Knight extends Piece {

    private static final String emblem = "N";
    private static final float SCORE = 2.5f;

    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return emblem;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.rankDistance(target) == 2 && source.fileDistance(target) == 1 ||
            source.rankDistance(target) == 1 && source.fileDistance(target) == 2;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
