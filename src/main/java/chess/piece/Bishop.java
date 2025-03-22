package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop implements ChessPiece {
    // 대각선으로 원하는만큼 이동 가능
    private final Color color;
    private final List<Movement> movements = List.of(
            Movement.LEFT_UP,
            Movement.RIGHT_UP,
            Movement.LEFT_DOWN,
            Movement.RIGHT_DOWN
    );

    public Bishop(Color color) {
        this.color = color;
    }

    public List<Position> getAvailableDestinations(Position startPosition, Map<Position, ChessPiece> positions) {
        List<Position> destinations = new ArrayList<>();

        // LEFT_UP
        // todo: 이동 가능한 위치(장애물이 없고 보드를 벗어나지 않은 구간)까지 구하기
        Position currentPosition = startPosition;
        while (currentPosition.canMoveLeftUp()) {
            currentPosition = currentPosition.move(Movement.LEFT_UP);
            destinations.add(currentPosition);
        }

        // RIGHT_UP
        currentPosition = startPosition;
        while (currentPosition.canMoveRightUp()) {
            currentPosition = currentPosition.move(Movement.RIGHT_UP);
            destinations.add(currentPosition);
        }

        // LEFT_DOWN
        currentPosition = startPosition;
        while (currentPosition.canMoveLeftDown()) {
            currentPosition = currentPosition.move(Movement.LEFT_DOWN);
            destinations.add(currentPosition);
        }

        // RIGHT_DOWN
        currentPosition = startPosition;
        while (currentPosition.canMoveRightDown()) {
            currentPosition = currentPosition.move(Movement.RIGHT_DOWN);
            destinations.add(currentPosition);
        }

        return destinations;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
