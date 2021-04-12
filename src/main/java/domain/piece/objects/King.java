package domain.piece.objects;

import domain.exception.InvalidTurnException;
import domain.exception.PieceCannotMoveException;
import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King extends Piece {
    private static final Score SCORE = Score.of(0);

    private King(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static King of(String name, boolean isBlack) {
        return new King(name, isBlack);
    }

    public static Map<Position, King> initialKingPieces() {
        return new HashMap<Position, King>() {{
            put(Position.of("e8"), King.of("K", true));
            put(Position.of("e1"), King.of("k", false));
        }};
    }

    private boolean movablePosition(Position start, Position end) {
        List<Direction> directions = new ArrayList<>();
        directions.addAll(Direction.linearDirection());
        directions.addAll(Direction.diagonalDirection());

        return directions.stream()
                .map(direction -> start.move(direction))
                .anyMatch(nextPosition -> nextPosition.validPosition() && nextPosition.equals(end));
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
        return true;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
