package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.List;

public final class Pawn extends GamePiece {

    private static final String INITIAL = "P";
    private static final int BLACK_DIRECTION = 1;
    private static final int WHITE_DIRECTION = -1;
    private static final int DOUBLE_FORWARD = 2;
    private static final double SINGLE_PAWN_SCORE = 1;
    private static final double MULTIPLE_PAWN_SCORE = 0.5;
    private static final int MULTIPLE_SCORE_LIMIT = 1;

    private boolean initPosition = true;

    public Pawn(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference, Piece targetPiece) {
        if (isSideEqualTo(Side.NONE)) {
            return false;
        }

        if (diagonal(rowDifference, columnDifference)) {
            checkOpponentPieceNotExistInToPosition(targetPiece);
        }
        if (forward(rowDifference, columnDifference)) {
            checkPieceExistInToPosition(targetPiece);
        }

        if (isSideEqualTo(Side.BLACK)) {
            return movableOneOrTwoSquare(rowDifference, columnDifference, BLACK_DIRECTION);
        }
        return movableOneOrTwoSquare(rowDifference, columnDifference, WHITE_DIRECTION);
    }

    private boolean diagonal(int rowDifference, int columnDifference) {
        return rowDifference == 1 && columnDifference == 1;
    }

    private boolean forward(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }
        return Math.abs(rowDifference) < 3;
    }

    private void checkOpponentPieceNotExistInToPosition(Piece targetPiece) {
        if (targetPiece.isBlank()) {
            throw new InvalidMovementException("이동하려는 위치에 상대 기물이 존재하지 않습니다.");
        }
    }

    private void checkPieceExistInToPosition(Piece targetPiece) {
        if (!targetPiece.isBlank()) {
            throw new InvalidMovementException("이동하려는 위치에 기물이 존재합니다.");
        }
    }

    private boolean movableOneOrTwoSquare(int rowDifference, int columnDifference, int direction) {
        if (oneSquareForwardOrDiagonal(rowDifference, columnDifference, direction)) {
            return true;
        }
        return twoSquareForward(rowDifference, columnDifference, direction);
    }

    private boolean oneSquareForwardOrDiagonal(int rowDifference, int columnDifference,
            int direction) {
        return rowDifference == direction && Math.abs(columnDifference) < DOUBLE_FORWARD;
    }

    private boolean twoSquareForward(int rowDifference, int columnDifference, int direction) {
        if (!initPosition) {
            return false;
        }
        if (columnDifference != 0) {
            return false;
        }
        return rowDifference == direction * DOUBLE_FORWARD;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.route(from, to);
    }

    @Override
    public void moved() {
        initPosition = false;
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    public static double scoreByCount(int count) {
        if (count == MULTIPLE_SCORE_LIMIT) {
            return SINGLE_PAWN_SCORE;
        }
        if (count > MULTIPLE_SCORE_LIMIT) {
            return count * MULTIPLE_PAWN_SCORE;
        }
        throw new IllegalArgumentException("유효하지 않은 count 입니다");
    }
}
