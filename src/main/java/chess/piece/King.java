package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class King implements ChessPiece {
    private final Color color;
    private final List<Movement> movements = List.of(
            Movement.UP,
            Movement.DOWN,
            Movement.LEFT,
            Movement.RIGHT,
            Movement.LEFT_UP,
            Movement.RIGHT_UP,
            Movement.LEFT_DOWN,
            Movement.RIGHT_DOWN
    );

    public King(Color color) {
        this.color = color;
    }

    @Override
    public List<Position> getAvailableDestinations(Position startPosition, Map<Position, ChessPiece> positions) {
        List<Position> destinations = new ArrayList<>();
        for (Movement movement : movements) {
            if (startPosition.canMove(movement)) {
                destinations.add(startPosition.move(movement));
            }
        }
        return destinations;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
