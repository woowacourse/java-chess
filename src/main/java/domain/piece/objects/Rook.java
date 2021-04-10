package domain.piece.objects;

import domain.exception.InvalidTurnException;
import domain.exception.PieceCannotMoveException;
import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {
    private static final Score SCORE = Score.of(5);

    private Rook(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static Rook of(String name, boolean color) {
        return new Rook(name, color);
    }

    public static Map<Position, Rook> initialRookPieces() {
        return new HashMap<Position, Rook>() {{
            put(Position.of("a8"), Rook.of("R", true));
            put(Position.of("h8"), Rook.of("R", true));
            put(Position.of("a1"), Rook.of("r", false));
            put(Position.of("h1"), Rook.of("r", false));
        }};
    }

    private boolean movablePosition(Position start, Position end) {
        Direction direction = getLinearDirection(start, end);
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
        if (!isLinear(start, end)) {
            throw new RuntimeException("올바른 방향이 아닙니다!");
        }
        return getLinearDirection(start, end);
    }

    @Override
    public boolean existPath() {
        return false;
    }
}
