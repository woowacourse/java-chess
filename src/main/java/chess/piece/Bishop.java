package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop implements ChessPiece {
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

        for (Movement movement : movements) {
            Position currentPosition = startPosition;
            while (currentPosition.canMove(movement)) {
                currentPosition = currentPosition.move(movement);
                if (positions.containsKey(currentPosition)) {
                    break;
                }
                destinations.add(currentPosition);
            }
        }

        return destinations;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
