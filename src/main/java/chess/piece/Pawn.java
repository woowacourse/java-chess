package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pawn implements ChessPiece {
    private final Color color;
    private boolean isFirstMove;

    public Pawn(Color color) {
        this.color = color;
        isFirstMove = true;
    }

    @Override
    public Position move(Position from, Position to, Map<Position, ChessPiece> positions) {
        isFirstMove = false;
        return null;
    }

    @Override
    public List<Position> getAvailableDestinations(Position startPosition, Map<Position, ChessPiece> positions) {
        List<Position> destinations = new ArrayList<>();
        for (Movement movement : getMovements()) {
            if (startPosition.canMove(movement)) {
                destinations.add(startPosition.move(movement));
            }
        }
        return destinations;
    }

    private List<Movement> getMovements() {
        if (isFirstMove) {
            return List.of(
                    Movement.DOWN,
                    Movement.DOWN_DOWN
            );
        }
        return List.of(
                Movement.DOWN
        );
    }

    @Override
    public Color getColor() {
        return color;
    }
}
