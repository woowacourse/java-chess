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
            if (canMove(startPosition, movement, positions)) {
                destinations.add(startPosition.move(movement));
            }
        }
        return destinations;
    }

    private List<Movement> getMovements() {
        if (color.isBlack()) {
            if (isFirstMove) {
                return List.of(
                        Movement.DOWN,
                        Movement.DOWN_DOWN,
                        Movement.LEFT_DOWN,
                        Movement.RIGHT_DOWN
                );
            }
            return List.of(
                    Movement.DOWN,
                    Movement.LEFT_DOWN,
                    Movement.RIGHT_DOWN
            );
        }
        if (isFirstMove) {
            return List.of(
                    Movement.UP,
                    Movement.UP_UP,
                    Movement.LEFT_UP,
                    Movement.RIGHT_UP
            );
        }
        return List.of(
                Movement.UP,
                Movement.LEFT_UP,
                Movement.RIGHT_UP
        );
    }

    private boolean canMove(Position startPosition, Movement movement, Map<Position, ChessPiece> positions) {
        if (!startPosition.canMove(movement)) {
            return false;
        }
        Position targetPosition = startPosition.move(movement);
        if (movement.isDiagonal()) {
            // 대각선 방향인 경우 - 상대 기물이 존재하는지 확인
            return positions.containsKey(targetPosition) && positions.get(targetPosition).getColor() != color;
        }
        // 빈 칸 확인
        return !positions.containsKey(targetPosition);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
