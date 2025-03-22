package chess.piece;

import chess.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bishop {

    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {

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
}
