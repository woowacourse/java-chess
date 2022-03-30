package chess.model.piece;

import static chess.model.PieceColor.*;
import static chess.model.boardinitializer.defaultInitializer.*;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;
    private static final int FIST_MOVE_DISTANCE = 2;
    private static final int DEFAULT_MOVE_DISTANCE = 1;

    private static final Piece PAWN_WHITE = new Pawn(WHITE);
    private static final Piece PAWN_BLACK = new Pawn(BLACK);

    private Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Piece colorOf(PieceColor pieceColor) {
        if (pieceColor.isWhite()) {
            return PAWN_WHITE;
        }
        return PAWN_BLACK;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        if (moveType.isEnemy()) {
            return isCaptureMoving(path);
        }

        return isForward(path);
    }

    private boolean isCaptureMoving(Path path) {
        if (isSameColor(WHITE)) {
            return path.isUpDiagonal();
        }

        return path.isDownDiagonal();
    }

    private boolean isForward(Path path) {
        if (isSameColor(WHITE)) {
            return isForwardWhite(path);
        }

        return isForwardBlack(path);
    }

    private boolean isForwardWhite(Path path) {
        if (isFirstMove(path)) {
            return path.isUpStraight(FIST_MOVE_DISTANCE);
        }

        return path.isUpStraight(DEFAULT_MOVE_DISTANCE);
    }

    private boolean isForwardBlack(Path path) {
        if (isFirstMove(path)) {
            return path.isDownStraight(FIST_MOVE_DISTANCE);
        }

        return path.isDownStraight(DEFAULT_MOVE_DISTANCE);
    }

    private boolean isFirstMove(Path path) {
        if (isSameColor(WHITE)) {
            return path.isSourceRankOf(PAWN_WHITE_INIT_RANK);
        }
        return path.isSourceRankOf(PAWN_BLACK_INIT_RANK);
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
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
