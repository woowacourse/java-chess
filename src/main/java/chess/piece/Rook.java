package chess.piece;

import chess.Color;
import chess.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public List<Position> calculateCanMovePosition(Position departure, Position arrival) {
        List<Position> upResult = calculateUp(departure, arrival);
        List<Position> downResult = calculateDown(departure, arrival);
        List<Position> leftResult = calculateLeft(departure, arrival);
        List<Position> rightResult = calculateRight(departure, arrival);

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
