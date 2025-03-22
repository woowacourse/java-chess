package chess;

public record Position(
        Column column,
        Row row
) {
    public Position(final Row row, final Column column) {
        this(column, row);
    }

    public boolean canMoveUp() {
        return row.canMoveUp(1);
    }

    public boolean canMoveUp(final int step) {
        return row.canMoveUp(step);
    }

    public Position moveUp() {
        return moveUp(1);
    }

    public Position moveUp(final int step) {
        return new Position(row.moveUp(step), column);
    }

    public boolean canMoveDown() {
        return canMoveDown(1);
    }

    public boolean canMoveDown(final int step) {
        return row.canMoveDown(step);
    }

    public Position moveDown() {
        return moveDown(1);
    }

    public Position moveDown(final int step) {
        return new Position(row.moveDown(step), column);
    }

    public boolean canMoveLeft() {
        return canMoveLeft(1);
    }

    public boolean canMoveLeft(final int step) {
        return column.canMoveLeft(step);
    }

    public Position moveLeft() {
        return moveLeft(1);
    }

    public Position moveLeft(final int step) {
        return new Position(row, column.moveLeft(step));
    }

    public boolean canMoveRight() {
        return canMoveRight(1);
    }

    public boolean canMoveRight(final int step) {
        return column.canMoveRight(step);
    }

    public Position moveRight() {
        return moveRight(1);
    }

    public Position moveRight(final int step) {
        return new Position(row, column.moveRight(step));
    }

    public boolean canMoveLeftUp() {
        return canMoveLeft() && canMoveUp();
    }

    public Position moveLeftUp() {
        return moveLeft().moveUp();
    }

    public boolean canMoveLeftDown() {
        return canMoveLeft() && canMoveDown();
    }

    public Position moveLeftDown() {
        return moveLeft().moveDown();
    }

    public boolean canMoveRightUp() {
        return canMoveUp() && canMoveRight();
    }

    public Position moveRightUp() {
        return moveRight().moveUp();
    }

    public boolean canMoveRightDown() {
        return canMoveRight() && canMoveDown();
    }

    public Position moveRightDown() {
        return moveRight().moveDown();
    }

    public boolean isTop() {
        return row.isTop();
    }

    public boolean isBottom() {
        return row.isBottom();
    }

    public boolean isFarLeft() {
        return column.isFarLeft();
    }

    public boolean isFarRight() {
        return column.isFarRight();
    }

    public boolean canMove(final Movement movement) {
        return canMoveVertical(movement.y()) && canMoveHorizontal(movement.x());
    }

    public boolean canMoveVertical(final int step) {
        if (step > 0) {
            return canMoveUp(step);
        }
        if (step < 0) {
            return canMoveDown(-step);
        }
        return true;
    }

    public boolean canMoveHorizontal(final int step) {
        if (step > 0) {
            return canMoveRight(step);
        }
        if (step < 0) {
            return canMoveLeft(-step);
        }
        return true;
    }

    public Position move(final Movement movement) {
        return moveVertical(movement.y()).moveHorizontal(movement.x());
    }

    public Position moveVertical(final int step) {
        if (step > 0) {
            return moveUp(step);
        }
        if (step < 0) {
            return moveDown(-step);
        }
        return this;
    }

    public Position moveHorizontal(final int step) {
        if (step > 0) {
            return moveRight(step);
        }
        if (step < 0) {
            return moveLeft(-step);
        }
        return this;
    }

    public boolean isRow(Row row) {
        return this.row == row;
    }
}
