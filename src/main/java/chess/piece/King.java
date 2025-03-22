package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

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
        return movements.stream()
                .filter(startPosition::canMove)
                .map(startPosition::move)
                .filter(position -> canMove(position, positions))
                .toList();
    }

    private boolean canMove(Position targetPosition, Map<Position, ChessPiece> positions) {
        return !positions.containsKey(targetPosition) || positions.get(targetPosition).getColor() != color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
