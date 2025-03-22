package chess.piece;

import chess.Color;
import chess.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }

    @Override
    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {

        List<Position> upResult = calculateUp(departure, arrival);
        List<Position> downResult = calculateDown(departure, arrival);
        List<Position> leftResult = calculateLeft(departure, arrival);
        List<Position> rightResult = calculateRight(departure, arrival);
        List<Position> rightUpResult = calculateRightUp(departure, arrival);
        List<Position> leftDownResult = calculateLeftDown(departure, arrival);
        List<Position> leftUpResult = calculateLeftUp(departure, arrival);
        List<Position> rightDownResult = calculateRightDown(departure, arrival);

        if (!rightUpResult.isEmpty()) {
            return rightUpResult;
        }
        if (!leftDownResult.isEmpty()) {
            return leftDownResult;
        }
        if (!leftUpResult.isEmpty()) {
            return leftUpResult;
        }
        if (!rightDownResult.isEmpty()) {
            return rightDownResult;
        }

        if (!upResult.isEmpty()) {
            return upResult;
        }
        if (!downResult.isEmpty()) {
            return downResult;
        }
        if (!leftResult.isEmpty()) {
            return leftResult;
        }
        if (!rightResult.isEmpty()) {
            return rightResult;
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
    }

    private List<Position> calculateRightUp(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveRightUp()) {
            Position movedPosition = movedDeparture.moveRightUp();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }

    private List<Position> calculateLeftDown(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveLeftDown()) {
            Position movedPosition = movedDeparture.moveLeftDown();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }
    private List<Position> calculateLeftUp(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveLeftUp()) {
            Position movedPosition = movedDeparture.moveLeftUp();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }
    private List<Position> calculateRightDown(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveRightDown()) {
            Position movedPosition = movedDeparture.moveRightDown();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }

    private List<Position> calculateUp(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveUp()) {
            Position movedPosition = movedDeparture.moveUp();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }

    private List<Position> calculateDown(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveDown()) {
            Position movedPosition = movedDeparture.moveDown();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }
    private List<Position> calculateLeft(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveLeft()) {
            Position movedPosition = movedDeparture.moveLeft();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }
    private List<Position> calculateRight(Position departure, Position arrival) {
        List<Position> tmpPosition = new ArrayList<>();
        Position movedDeparture = departure.copyOf();
        while (movedDeparture.canMoveRight()) {
            Position movedPosition = movedDeparture.moveRight();
            tmpPosition.add(movedPosition);
            if (movedPosition.equals(arrival)) {
                return tmpPosition;
            }
            movedDeparture = movedPosition;
        }
        return Collections.emptyList();
    }
}
