package chess.model.piece;

import static chess.vo.PieceColor.*;

import chess.vo.MoveType;
import chess.vo.PieceColor;
import chess.vo.Position;
import chess.vo.Rank;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Position source, Position target, MoveType moveType) {
        if (moveType == MoveType.ENEMY) {
            return isCaptureMoving(source, target);
        }

        if (pieceColor == WHITE) {
            return isForwardWhite(source, target);
        }

        return isForwardBlack(source, target);
    }

    private boolean isCaptureMoving(Position source, Position target) {
        if (pieceColor == WHITE) {
            return source.isDiagonal(target) && source.rankDisplacement(target) == 1
                && source.fileDistance(target) == 1;
        }
        return source.isDiagonal(target) && target.rankDisplacement(source) == 1
            && source.fileDistance(target) == 1;
    }

    private boolean isForwardWhite(Position source, Position target) {
        if (isFirstMove(source)) {
            return isForward(source, target, 2);
        }

        return isForward(source, target, 1);
    }

    private boolean isFirstMove(Position source) {
        return source.isRankOf(Rank.TWO) || source.isRankOf(Rank.SEVEN);
    }

    private boolean isForwardBlack(Position source, Position target) {
        if (isFirstMove(source)) {
            return isForward(target, source, 2);
        }

        return isForward(target, source, 1);
    }

    private boolean isForward(Position source, Position target, int amount) {
        return source.rankDisplacement(target) > 0
            && source.rankDisplacement(target) <= amount && source.isSameFile(target);
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
