package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class PawnMoveStrategy extends MoveStrategy {
    private static final String NO_MOVE_MESSAGE = "폰이 이동할 수 없는 위치입니다.";
    private static final String NO_MOVE_MESSAGE_DIAGONAL = "대각선 방향에 상대 기물이 없으면 이동할 수 없습니다.";
    private static final int INIT_MAX_DISTANCE = 2;

    public static List<Direction> pawnDirection(Team team) {
        return Direction.getAbsoluteDirections(team, List.of(Direction.TOP, Direction.TOP_LEFT, Direction.TOP_RIGHT));
    }

    @Override
    public boolean isValidateCanMove(Team team, Piece targetPiece, Position from, Position to) {
        List<Direction> directions = pawnDirection(team);
        Direction nowDirection = Direction.of(from, to);

        if (isInvalidDirection(from, to, directions)) {
            validateInitDirection(team, from, to, directions);
        }
        if (isDiagonal(nowDirection, directions) && !targetPiece.isOppositeTeam(team)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE_DIAGONAL);
        }

        return nowDirection != Direction.TOP && nowDirection != Direction.DOWN;
    }

    private boolean isDiagonal(Direction now, List<Direction> directions) {
        return now == directions.get(1) || now == directions.get(2);
    }

    private void validateInitDirection(Team team, Position from, Position to, List<Direction> directions) {
        if (isInitLine(team, from) && isInitDistance(from, to, directions.get(0))) {
            return;
        }
        throw new IllegalArgumentException(NO_MOVE_MESSAGE);
    }

    private boolean isInitLine(Team team, Position from) {
        return (team == Team.BLACK && from.isEqualRank(Rank.SEVEN) ||
                (team == Team.WHITE && from.isEqualRank(Rank.TWO)));
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
