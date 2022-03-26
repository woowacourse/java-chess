package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.PieceColor;
import chess.vo.Position;

public class Knight extends Piece {

    private static final String EMBLEM = "N";
    private static final double SCORE = 2.5;

    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target, MoveType moveType) {
        return source.rankDistance(target) == 2 && source.fileDistance(target) == 1 ||
            source.rankDistance(target) == 1 && source.fileDistance(target) == 2;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
