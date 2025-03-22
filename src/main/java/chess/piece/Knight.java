package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Knight implements ChessPiece {
    private final Color color;
    private final List<Movement> movements = List.of(
            Movement.UP_UP_LEFT,
            Movement.UP_UP_RIGHT,
            Movement.DOWN_DOWN_LEFT,
            Movement.DOWN_DOWN_RIGHT,
            Movement.LEFT_LEFT_UP,
            Movement.LEFT_LEFT_DOWN,
            Movement.RIGHT_RIGHT_UP,
            Movement.RIGHT_RIGHT_DOWN
    );

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public List<Position> getAvailableDestinations(Position startPosition, Map<Position, ChessPiece> positions) {
        List<Position> destinations = new ArrayList<>();
        for (Movement movement : movements) {
            if (startPosition.canMove(movement) && canMove(startPosition.move(movement), positions)) {
                destinations.add(startPosition.move(movement));
            }
        }
        return destinations;
    }

    private boolean canMove(Position targetPosition, Map<Position, ChessPiece> positions) {
        return !positions.containsKey(targetPosition) || positions.get(targetPosition).getColor() != color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
