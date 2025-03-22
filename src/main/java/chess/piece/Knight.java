package chess.piece;

import chess.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight {

    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {

        List<Position> upAndLeftUpResult = upAndLeftUp(departure);
        List<Position> upAndRightUpResult = upAndRightUp(departure);
        List<Position> downAndLeftDownResult = downAndLeftDown(departure);
        List<Position> downAndRightDownResult = downAndRightDown(departure);
        List<Position> leftAndLeftDownResult = leftAndLeftDown(departure);
        List<Position> leftAndLeftUpResult = leftAndLeftUp(departure);
        List<Position> rightAndRightUpResult = rightAndRightUp(departure);
        List<Position> rightAndRightDownResult = rightAndRightDown(departure);


        if (!upAndLeftUpResult.isEmpty() && upAndLeftUpResult.getLast().equals(arrival)) {
            return upAndLeftUpResult;
        }
        if (!upAndRightUpResult.isEmpty() && upAndRightUpResult.getLast().equals(arrival)) {
            return upAndRightUpResult;
        }
        if (!downAndLeftDownResult.isEmpty() &&downAndLeftDownResult.getLast().equals(arrival)) {
            return downAndLeftDownResult;
        }
        if (!downAndRightDownResult.isEmpty() &&downAndRightDownResult.getLast().equals(arrival)) {
            return downAndRightDownResult;
        }
        if (!leftAndLeftDownResult.isEmpty() &&leftAndLeftDownResult.getLast().equals(arrival)) {
            return leftAndLeftDownResult;
        }
        if (!leftAndLeftUpResult.isEmpty() &&leftAndLeftUpResult.getLast().equals(arrival)) {
            return leftAndLeftUpResult;
        }
        if (!rightAndRightUpResult.isEmpty() &&rightAndRightUpResult.getLast().equals(arrival)) {
            return rightAndRightUpResult;
        }
        if (!rightAndRightDownResult.isEmpty() &&rightAndRightDownResult.getLast().equals(arrival)) {
            return rightAndRightDownResult;
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
    }

    private List<Position> upAndLeftUp(Position departure) {
        Position movedPosition;
        if (departure.canMoveUp()) {
            movedPosition = departure.moveUp();
        } else {
            return Collections.emptyList();
        }
        if (movedPosition.canMoveLeftUp()) {
            return List.of(departure.moveUp(), movedPosition.moveLeftUp());
        }
        return Collections.emptyList();
    }

    private List<Position> upAndRightUp(Position departure) {
        Position movedPosition;
        if (departure.canMoveUp()) {
            movedPosition = departure.moveUp();
        } else {
            return Collections.emptyList();
        }
        if (movedPosition.canMoveRightUp()) {
            return List.of(departure.moveUp(), movedPosition.moveRightUp());
        }
        return Collections.emptyList();
    }

    private List<Position> downAndLeftDown(Position departure) {
        Position movedPosition;
        if (departure.canMoveDown()) {
            movedPosition = departure.moveDown();
        } else {
            return Collections.emptyList();
        }
        if (movedPosition.canMoveLeftDown()) {
            return List.of(departure.moveDown(), movedPosition.moveLeftDown());
        }
        return Collections.emptyList();
    }

    private List<Position> downAndRightDown(Position departure) {
        Position movedPosition;
        if (departure.canMoveDown()) {
            movedPosition = departure.moveDown();
        } else {
            return Collections.emptyList();
        }
        if (movedPosition.canMoveRightDown()) {
            return List.of(departure.moveDown(), movedPosition.moveRightDown());
        }
        return Collections.emptyList();
    }

    private List<Position> leftAndLeftDown(Position departure) {
        Position movedPosition;
        if (departure.canMoveLeft()) {
            movedPosition = departure.moveLeft();
        } else{
            return Collections.emptyList();
        }
        if (movedPosition.canMoveLeftDown()) {
            return List.of(departure.moveLeft(), movedPosition.moveLeftDown());
        }
        return Collections.emptyList();
    }

    private List<Position> leftAndLeftUp(Position departure) {
        Position movedPosition;
        if (departure.canMoveLeft()) {
            movedPosition = departure.moveLeft();
        } else{
            return Collections.emptyList();
        }
        if (movedPosition.canMoveLeftUp()) {
            return List.of(departure.moveLeft(), movedPosition.moveLeftUp());
        }
        return Collections.emptyList();
    }

    private List<Position> rightAndRightUp(Position departure) {
        Position movedPosition;
        if (departure.canMoveRight()) {
            movedPosition = departure.moveRight();
        } else{
            return Collections.emptyList();
        }
        if (movedPosition.canMoveRightUp()) {
            return List.of(departure.moveRight(), movedPosition.moveRightUp());
        }
        return Collections.emptyList();
    }

    private List<Position> rightAndRightDown(Position departure) {
        Position movedPosition;
        if (departure.canMoveRight()) {
            movedPosition = departure.moveRight();
        } else{
            return Collections.emptyList();
        }
        if (movedPosition.canMoveRightDown()) {
            return List.of(departure.moveRight(), movedPosition.moveRightDown());
        }
        return Collections.emptyList();
    }
}
