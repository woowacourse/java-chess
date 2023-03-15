package domain.piece;

import domain.position.Position;

import java.util.List;

public final class Pawn extends Piece {

    public Pawn(Color color) {
        super(PieceName.PAWN, color);
    }

    @Override
    public boolean isMovablePath(Position start, List<Position> path) {
        if (isBlack() && start.isBlackPawnInitialRow()) {
            return isMovableNonInitialRowPawn(start, path) ||
                    path.contains(start.moveDown().moveDown()) && path.size() == 2;
        }
        if (isWhite() && start.isWhitePawnInitialRow()) {
            return isMovableNonInitialRowPawn(start, path) ||
                    path.contains(start.moveUp().moveUp()) && path.size() == 2;
        }
        return isMovableNonInitialRowPawn(start, path);
    }

    private boolean isMovableNonInitialRowPawn(Position start, List<Position> path) {
        return isMovableDirection(start, path.get(0)) && isMovableDistance(path.size());
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        if (isBlack()) {
            return start.moveDown().equals(nextPosition) ||
                    start.moveDownRight().equals(nextPosition) ||
                    start.moveDownLeft().equals(nextPosition);
        }
        return start.moveUp().equals(nextPosition) ||
                start.moveUpRight().equals(nextPosition) ||
                start.moveUpLeft().equals(nextPosition);
        /*
        black일때
        초기 위치가 아닐 때 전진 가능한 방향 : moveDown, moveDownLeft, moveDownRight
        초기 위치일 때 전진 가능한 방향 : moveDown, moveDownLeft, moveDownRight, moveDown*2

        white일때
        초기 위치가 아닐 때 전진 가능한 방향 : moveUp, moveUpLeft, moveUpRight
        초기 위치일 때 전진 가능한 방향 : moveUp, moveUpLeft, moveUpRight, moveUp*2
         */
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        return distance == 1;
    }
}
