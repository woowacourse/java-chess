package chess.domain.board;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square {
    private final Position x;
    private final Position y;

    public Square(Position x, Position y) {
        this.x = x;
        this.y = y;
    }

    public Square moveLeft(int moveCnt) {
        return new Square(x.moveBack(moveCnt), y);
    }

    public Square moveRight(int moveCnt) {
        return new Square(x.moveGo(moveCnt), y);
    }

    public Square moveUp(int moveCnt) {
        return new Square(x, y.moveGo(moveCnt));
    }

    public Square moveDown(int moveCnt) {
        return new Square(x, y.moveBack(moveCnt));
    }

    public List<Square> moveUpToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<Position> yPositions = y.moveGoToEnd();

        for (Position yPosition : yPositions) {
            movableList.add(new Square(x, yPosition));
        }

        return movableList;
    }

    public List<Square> moveDownToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<Position> yPositions = y.moveBackToEnd();

        for (Position yPosition : yPositions) {
            movableList.add(new Square(x, yPosition));
        }

        return movableList;
    }

    public List<Square> moveRightToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<Position> xPositions = x.moveGoToEnd();

        for (Position xPosition : xPositions) {
            movableList.add(new Square(xPosition, y));
        }
        return movableList;
    }

    public List<Square> moveLeftToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<Position> xPositions = x.moveBackToEnd();

        for (Position xPosition : xPositions) {
            movableList.add(new Square(xPosition, y));
        }
        return movableList;
    }

    public List<Square> moveUpRightToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveUpRight();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveUpRight();
        }

        return movableList;
    }

    public List<Square> moveUpLeftToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveUpLeft();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveUpLeft();
        }

        return movableList;
    }

    public List<Square> moveDownRightToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveDownRight();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveDownRight();
        }

        return movableList;
    }

    public List<Square> moveDownLeftToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveDownLeft();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveDownLeft();
        }

        return movableList;
    }

    public Square moveUpLeft() {
        Square moved = new Square(x.moveBack(1), y.moveGo(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public Square moveUpRight() {
        Square moved = new Square(x.moveGo(1), y.moveGo(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public Square moveDownLeft() {
        Square moved = new Square(x.moveBack(1), y.moveBack(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public Square moveDownRight() {
        Square moved = new Square(x.moveGo(1), y.moveBack(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public boolean isLocatedSameLine(Square moved) {
        return moved.isSameX(this.x) || moved.isSameY(this.y);
    }

    public boolean isSameX(Position x) {
        return this.x.equals(x);
    }

    public boolean isSameY(Position y) {
        return this.y.equals(y);
    }

    public boolean isVertical(Square square) {
        return this.x.equals(square.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square that = (Square) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "" + x + y;
    }
}
