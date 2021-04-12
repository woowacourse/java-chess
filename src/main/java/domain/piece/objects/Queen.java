package domain.piece.objects;

import domain.exception.InvalidTurnException;
import domain.exception.PieceCannotMoveException;
import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.HashMap;
import java.util.Map;

public class Queen extends Piece {
    private static final Score SCORE = Score.of(9);

    private Queen(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static Queen of(String name, boolean color) {
        return new Queen(name, color);
    }

    public static Map<Position, Queen> initialQueenPieces() {
        return new HashMap<Position, Queen>() {{
            put(Position.of("d8"), Queen.of("Q", true));
            put(Position.of("d1"), Queen.of("q", false));
        }};
    }

    private boolean movablePosition(Position start, Position end) {
        Direction direction = getLinearDirection(start, end);

        if (isDiagonal(start, end)) {
            direction = getDiagonalDirection(start, end);
        }

        Position temp = start.move(direction);
        while (!temp.equals(end) && temp.validPosition()) {
            temp = temp.move(direction);
        }
        return temp.equals(end) && temp.validPosition();
    }

    @Override
    public void checkMovable(Position start, Position end, boolean turn) {
        if (!isSameColor(turn)) {
            throw new InvalidTurnException();
        }

        if (!movablePosition(start, end)) {
            throw new PieceCannotMoveException(name());
        }
    }

    @Override
    public Direction direction(Position start, Position end) {
        if (isLinear(start, end)) {
            return getLinearDirection(start, end);
        }

        if (isDiagonal(start, end)) {
            return getDiagonalDirection(start, end);
        }

        throw new RuntimeException("올바른 방향이 아닙니다!");
    }

    @Override
    public boolean existPath() {
        return false;
    }
}
