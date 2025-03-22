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
                if (!canMove(currentPosition, positions)) {
                    break;
                }
                destinations.add(currentPosition);
                if (positions.containsKey(currentPosition)) {
                    break;
                }
            }
        }

        return destinations;
    }

    private boolean canMove(Position targetPosition, Map<Position, ChessPiece> positions) {
        return !positions.containsKey(targetPosition) || canCatch(targetPosition, positions);
    }

    private boolean canCatch(Position targetPosition, Map<Position, ChessPiece> positions) {
        return positions.containsKey(targetPosition) && positions.get(targetPosition).getColor() != color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
