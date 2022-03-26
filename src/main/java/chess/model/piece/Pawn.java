package chess.model.piece;

import static chess.vo.PieceColor.*;

import chess.vo.MoveType;
import chess.vo.Path;
import chess.vo.PieceColor;
import chess.vo.Rank;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        if (moveType == MoveType.ENEMY) {
            return isCaptureMoving(path);
        }

        if (pieceColor == WHITE) {
            return isForwardWhite(path);
        }

        return isForwardBlack(path);
    }

    private boolean isCaptureMoving(Path path) {
        if (pieceColor == WHITE) {
            return path.isUpDiagonal();
        }
        return path.isDownDiagonal();
    }

    private boolean isForwardWhite(Path path) {
        if (isFirstMove(path)) {
            return path.isUpStraight(2);
        }

        return path.isUpStraight(1);
    }

    private boolean isFirstMove(Path path) {
        return path.isSourceRankOf(Rank.TWO) || path.isSourceRankOf(Rank.SEVEN);
    }

    private boolean isForwardBlack(Path path) {
        if (isFirstMove(path)) {
            return path.isDownStraight(2);
        }

        return path.isDownStraight(1);
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
