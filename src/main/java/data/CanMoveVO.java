package data;

public class CanMoveVO {
    private final boolean canMove;

    public CanMoveVO(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isCanMove() {
        return canMove;
    }
}
