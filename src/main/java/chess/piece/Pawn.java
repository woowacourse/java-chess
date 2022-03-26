package chess.piece;

import static chess.PieceColor.*;

import chess.MoveType;
import chess.PieceColor;
import chess.Position;
import chess.Rank;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Position source, Position target, MoveType moveType) {
        // MoveType == ENEMY
        if (moveType == MoveType.ENEMY) {
            if (pieceColor == WHITE) {
                return source.isDiagonal(target) && source.rankDisplacement(target) == 1
                    && source.fileDistance(target) == 1;
            }

            return source.isDiagonal(target) && target.rankDisplacement(source) == 1
                && source.fileDistance(target) == 1;
        }

        // MoveType == EMPTY
        if (pieceColor == WHITE) {
            if (source.isRankOf(Rank.TWO)) {
                return isForward(source, target, 2);
            }

            return isForward(source, target, 1);
        }

        if (source.isRankOf(Rank.SEVEN)) {
            return isForward(target, source, 2);
        }

        return isForward(target, source, 1);
    }

    private boolean isForward(Position source, Position target, int amount) {
        return source.rankDisplacement(target) > 0 && source.rankDisplacement(target) <= amount && source.isSameFile(
            target);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
