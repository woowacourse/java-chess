package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {
    private static final int INIT_MAX_DISTANCE = 2;

    @Override
    public void canMove(Color color, Position from, Position to) {
        List<Direction> directions = Direction.pawnDirection(color);

        if (isInitLine(color, from)) {
            if (!isValidInitDirection(from, to, directions)) {
                throw new IllegalArgumentException("폰이 최초 위치일 때 이동할 수 없는 위치입니다.");
            }
            return;
        }

        if (!isValidDirection(from, to, directions)) {
            throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isInitLine(Color color, Position from) {
        return (color == Color.BLACK && from.isEqualRank(Rank.SEVEN) ||
                (color == Color.WHITE && from.isEqualRank(Rank.TWO)));
    }

    private boolean isValidInitDirection(Position from, Position to, List<Direction> directions) {
        return isInitDistance(from, to, directions.get(0)) ||
                isValidDirection(from, to, directions.subList(1, directions.size()));
    }

    private boolean isInitDistance(Position from, Position to, Direction direction) {
        return from.getXDistance(to) == direction.getX()
                && direction.getY() * from.getYDistance(to) <= INIT_MAX_DISTANCE;
    }

    private boolean isValidDirection(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .anyMatch(direction -> direction.isSameDirection(from, to));
    }
}
