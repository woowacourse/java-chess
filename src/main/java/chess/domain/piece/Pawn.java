package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Pawn implements ChessPiece {

    private static final int DEFAULT_DISTANCE = 1;
    private static final int OPTIONAL_DISTANCE = 1;
    private static final int WHITE_INITIAL_ROW_POSITION = 7;
    private static final int BLACK_INITIAL_ROW_POSITION = 2;

    private final Team team;
    private ChessBoardPosition position;

    public Pawn(Team team, ChessBoardPosition position) {
        this.team = team;
        this.position = position;
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        if (position.isDifferentColumn(targetPosition)) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }
        position = targetPosition;
    }

    private void forwardWhite(ChessBoardPosition targetPosition) {
        if (calculateRowDistance(position.getRow(), targetPosition.getRow()) != DEFAULT_DISTANCE) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }
        position = targetPosition;
    }

    private void forwardBlack(ChessBoardPosition targetPosition) {
        if (calculateRowDistance(targetPosition.getRow(), position.getRow()) != DEFAULT_DISTANCE) {
            throw new IllegalArgumentException();
        }
        position = targetPosition;
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    public boolean isSamePosition(ChessBoardPosition anotherPosition) {
        return position.equals(anotherPosition);
    }
}
