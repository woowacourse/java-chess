package chess.model.piece;

import static chess.model.PieceColor.*;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;
import chess.model.Rank;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;
    private static final int FIST_MOVE_DISTANCE = 2;
    private static final int DEFAULT_MOVE_DISTANCE = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        if (moveType == MoveType.ENEMY) {
            return isCaptureMoving(path);
        }

        if (isSameColor(WHITE)) {
            return isForwardWhite(path);
        }

        return isForwardBlack(path);
    }

    private boolean isCaptureMoving(Path path) {
        if (isSameColor(WHITE)) {
            return path.isUpDiagonal();
        }
        return path.isDownDiagonal();
    }

    private boolean isForwardWhite(Path path) {
        if (isFirstMove(path)) {
            return path.isUpStraight(FIST_MOVE_DISTANCE);
        }

        return path.isUpStraight(DEFAULT_MOVE_DISTANCE);
    }

    private boolean isFirstMove(Path path) {
        return path.isSourceRankOf(Rank.TWO) || path.isSourceRankOf(Rank.SEVEN);
    }

    private boolean isForwardBlack(Path path) {
        if (isFirstMove(path)) {
            return path.isDownStraight(FIST_MOVE_DISTANCE);
        }

        return path.isDownStraight(DEFAULT_MOVE_DISTANCE);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
