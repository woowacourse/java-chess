package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Pawn extends ChessPiece {

    private static final int DEFAULT_DISTANCE = 1;
    private static final int OPTIONAL_DISTANCE = 1;
    private static final int WHITE_INITIAL_ROW_POSITION = 7;
    private static final int BLACK_INITIAL_ROW_POSITION = 2;
    private static final String NAME = "PAWN";
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 폰이 이동할 수 없는 위치입니다.";

    public Pawn(Team team, ChessBoardPosition position) {
        super(NAME, team, position);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        if (position.isDifferentColumn(targetPosition)) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        if (team.isWhite()) {
            moveWhite(targetPosition);
            return;
        }
        moveBlack(targetPosition);
    }

    private void moveWhite(ChessBoardPosition targetPosition) {
        if (isWhiteInitialPosition()) {
            whiteInitialMove(targetPosition);
            return;
        }
        forwardWhite(targetPosition);
    }

    private boolean isWhiteInitialPosition() {
        return position.isSameRow(WHITE_INITIAL_ROW_POSITION);
    }

    private void whiteInitialMove(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        if (rowDistance != DEFAULT_DISTANCE && rowDistance != DEFAULT_DISTANCE + OPTIONAL_DISTANCE) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    private void forwardWhite(ChessBoardPosition targetPosition) {
        if (calculateRowDistance(position.getRow(), targetPosition.getRow()) != DEFAULT_DISTANCE) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    private void moveBlack(ChessBoardPosition targetPosition) {
        if (isBlackInitialPosition()) {
            blackInitialMove(targetPosition);
            return;
        }
        forwardBlack(targetPosition);
    }

    private boolean isBlackInitialPosition() {
        return position.isSameRow(BLACK_INITIAL_ROW_POSITION);
    }

    private void blackInitialMove(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(targetPosition.getRow(), position.getRow());
        if (rowDistance != DEFAULT_DISTANCE && rowDistance != DEFAULT_DISTANCE + OPTIONAL_DISTANCE) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    private void forwardBlack(ChessBoardPosition targetPosition) {
        if (calculateRowDistance(targetPosition.getRow(), position.getRow()) != DEFAULT_DISTANCE) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    public boolean isSamePosition(ChessBoardPosition anotherPosition) {
        return position.equals(anotherPosition);
    }

    public void moveDiagonal(ChessBoardPosition targetPosition) {
        if (team.isWhite()) {
            moveDiagonalWhite(targetPosition);
            return;
        }
        moveDiagonalBlack(targetPosition);
    }

    private void moveDiagonalWhite(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        if (rowDistance != DEFAULT_DISTANCE || columnDistance != DEFAULT_DISTANCE) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    private void moveDiagonalBlack(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(targetPosition.getRow(), position.getRow());
        int columnDistance = calculateColumnDistance(targetPosition.getColumn(), position.getColumn());
        if (rowDistance != DEFAULT_DISTANCE || columnDistance != DEFAULT_DISTANCE) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }
}
