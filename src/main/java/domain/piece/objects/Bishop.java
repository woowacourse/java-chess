package domain.piece.objects;

import domain.exception.InvalidTurnException;
import domain.exception.PieceCannotMoveException;
import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.HashMap;
import java.util.Map;

public class Bishop extends Piece {
    private static final Score SCORE = Score.of(3);

    private Bishop(String name, boolean color) {
        super(name, SCORE, color);
    }

    public static Bishop of(String name, boolean color) {
        return new Bishop(name, color);
    }

    public static Map<Position, Bishop> initialBishopPieces() {
        return new HashMap<Position, Bishop>() {{
            put(Position.of("c8"), Bishop.of("B", true));
            put(Position.of("f8"), Bishop.of("B", true));
            put(Position.of("c1"), Bishop.of("b", false));
            put(Position.of("f1"), Bishop.of("b", false));
        }};
    }

    private boolean movablePosition(Position start, Position end) {
        Direction direction = getDiagonalDirection(start, end);
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
    public boolean existPath() {
        return false;
    }

    @Override
    public Direction direction(Position start, Position end) {
        if (!isDiagonal(start, end)) {
            throw new RuntimeException("비숍은 대각선 방향만 가질 수 있습니다!");
        }
        return getDiagonalDirection(start, end);
    }
}
