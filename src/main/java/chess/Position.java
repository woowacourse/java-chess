package chess;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Position(
        Column column,
        Row row
) {
    public Position(final Row row, final Column column) {
        this(column, row);
    }

    public static Position from(String column, String row) {
        return new Position(Column.from(column), Row.from(row));
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

    //내 포지션으롭투 MoveMent들을 받아서 모든 곳으로 갈 수 있는가? 다시 말해서 이 기물 능력으로 거기 가는 거 가능?
    public Set<Position> findMoveAblePositions(Set<Movement> movements) {
        return movements.stream()
                .filter(this::canMove)
                .map(this::move)
                .collect(Collectors.toSet());
    }

    //지금 포지션에서 위 아래로 movement 몇 개 추가할 수 있는지 구한다
    // 추가 가능한 movement만큼 positions에 추가한다.
    public Set<Position> findSlidingAblePositions(Set<Movement> movements) {
        Set<Position> positions = new HashSet<>();

        for (Movement movement : movements) {
            Position current = this;
            while (current.canMove(movement)) {
                current = current.move(movement);
                positions.add(current);
            }
        }

        return positions;
    }

    public Set<Position> findBetweenSlidingAblePositions(Set<Movement> movements, Position toPosition) {
        Set<Position> positions = new HashSet<>();

        for (Movement movement : movements) {
            Position current = this;
            Set<Position> path = new LinkedHashSet<>();

            while (current.canMove(movement)) {
                current = current.move(movement);
                if (current.equals(toPosition)) {
                    path.remove(this);
                    path.remove(toPosition);
                    positions.addAll(path);
                    break;
                }
                path.add(current);
            }
        }

        return positions;
    }


    public Set<Position> rookBetweenPositions(Position toPosition) {
        Set<Position> betweenPositions = new HashSet<>();
        if (this.column == toPosition.column) {
            List<Row> betweenRows = this.row.betweenRows(toPosition.row);
            for (Row row : betweenRows) {
                betweenPositions.add(new Position(this.column, row));
            }
        }
        if (this.row == toPosition.row) {
            List<Column> betweenColumns = this.column.betweenColumns(toPosition.column);
            for (Column column : betweenColumns) {
                betweenPositions.add(new Position(column, this.row));
            }
        }

        return betweenPositions;
    }


    public boolean isStartWhitePawn() {
        if (row.equals(Row.TWO)) {
            return true;
        }
        return false;
    }

    public boolean isStartWithBlackPawn() {
        if (row.equals(Row.SEVEN)) {
            return true;
        }
        return false;
    }

    public Set<Position> findEatAblePositions(Set<Movement> movements) {
        return null;
    }
}
