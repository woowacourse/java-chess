package chess.piece;

import chess.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook {

    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {
        List<Position> rightUpResult = calculateUp(departure, arrival);
        List<Position> leftDownResult = calculateDown(departure, arrival);
        List<Position> leftUpResult = calculateLeft(departure, arrival);
        List<Position> rightDownResult = calculateRight(departure, arrival);

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
        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
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
