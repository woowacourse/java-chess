package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;
import java.util.List;

public class PawnMoveStrategy extends MoveStrategy {
    private static final int INIT_MAX_DISTANCE = 2;

    @Override
    public boolean isValidateCanMove(Color color, Piece targetPiece, Position from, Position to) {
        List<Direction> directions = pawnDirection(color);
        Direction nowDirection = Direction.of(from, to);

        if (isInvalidDirection(from, to, directions)) {
            validateInitDirection(color, from, to, directions);
        }
        if (isDiagonal(nowDirection, directions) && !targetPiece.isOppositeColor(color)) {
            throw new IllegalArgumentException("대각선 방향에 상대 기물이 없으면 이동할 수 없습니다.");
        }

        return nowDirection != Direction.TOP && nowDirection != Direction.DOWN;
    }

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        throw new IllegalArgumentException("상대방 기물을 알 수 없으면 이동할 수 없습니다.");
    }

    public static List<Direction> pawnDirection(Color color) {
        return Direction.getColorDirections(color, List.of(Direction.TOP, Direction.TOPLEFT, Direction.TOPRIGHT));
    }

    private boolean isDiagonal(Direction now, List<Direction> directions) {
        return now == directions.get(1) || now == directions.get(2);
    }

    private void validateInitDirection(Color color, Position from, Position to, List<Direction> directions) {
        if (isInitLine(color, from) && isInitDistance(from, to, directions.get(0))) {
            return;
        }
        throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
    }

    private boolean isInitLine(Color color, Position from) {
        return (color == Color.BLACK && from.isEqualRank(Rank.SEVEN) ||
                (color == Color.WHITE && from.isEqualRank(Rank.TWO)));
    }

    private boolean isInitDistance(Position from, Position to, Direction direction) {
        return from.getXDistance(to) == direction.getX()
                && direction.getY() * from.getYDistance(to) <= INIT_MAX_DISTANCE;
    }

    protected boolean isInvalidDirection(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .noneMatch(direction -> direction.isSameDistance(from, to));
    }

}
