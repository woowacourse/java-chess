package chess.piece;

import chess.PieceColor;
import chess.Position;

public class King extends Piece {

    private static final String EMBLEM = "K";
    private static final double SCORE = 0;

    public King(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return (source.isAllDirectional(target) && source.fileDistance(target) <= 1
            && source.rankDistance(target) <= 1);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
