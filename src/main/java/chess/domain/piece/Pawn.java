package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Pawn extends Piece {
    private static final char NAME_WHEN_BLACK = 'P';
    private static final char NAME_WHEN_WHITE = 'p';
    private static final int STEP_RANGE = 1;
    private static final int TWO_STEP_RANGE = 2;

    private boolean moved = false;

    public Pawn(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    public List<Direction> getDirectionsOnTwoStep() {
        if (isBlack()) {
            return Direction.blackPawnLinearDirection();
        }
        return Direction.whitePawnLinearDirection();
    }

    public boolean hasMoved(){
        return moved;
    }

    public void moved(){
        moved = true;
    }

    public void validatePawnMove(Piece targetPiece){
        validateTwoSteps(targetPiece);
        validateDiagonalMove(targetPiece);
        validateObstacleAhead(targetPiece);
    }

    private void validateTwoSteps(Piece targetPiece){
        if (hasMoved() && Math.abs(targetPiece.getPosition().getY() - getPosition().getY()) == 2) {
            throw new IllegalArgumentException("폰은 초기 자리에서만 두칸 이동 가능합니다.");
        }
    }

    private void validateDiagonalMove(Piece targetPiece){
        if (Math.abs(targetPiece.getPosition().getY() - getPosition().getY()) == 1 &&
                Math.abs(targetPiece.getPosition().getX() - getPosition().getX()) == 1 && targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 상대 말을 먹을 때만 대각선으로 이동이 가능합니다.");
        }
    }

    private void validateObstacleAhead(Piece targetPiece) {
        if (Math.abs(targetPiece.getPosition().getY() - getPosition().getY()) == 1 && Math.abs(targetPiece.getPosition().getX() - getPosition().getX()) == 0 && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 한칸 앞 말이 있으면 가지 못합니다.");
        }
    }

    @Override
    public List<Direction> getDirections() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    public int getTwoStepRange() {
        return TWO_STEP_RANGE;
    }

    @Override
    public int getStepRange() {
        return STEP_RANGE;
    }

    @Override
    public char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}