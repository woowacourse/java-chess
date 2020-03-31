package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Y;
import java.util.List;
import java.util.Map;

public class MoveInformation {

    private final Map<Position, Piece> board;
    private final Position from;
    private final Position to;

    public MoveInformation(final Map<Position, Piece> board, final Position from,
        final Position to) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException("start 위치에 기물이 없을 수 없습니다.");
        }
        this.board = board;
        this.from = from;
        this.to = to;
    }

    public boolean isStartEnd() {
        return from.equals(to);
    }

    public boolean isEndAdjacentToStart() {
        return to.equals(from.increase(0, 1))    // North
            || to.equals(from.increase(1, 1))    // NorthEast
            || to.equals(from.increase(1, 0))    // East
            || to.equals(from.increase(1, -1))   // SouthEast
            || to.equals(from.increase(0, -1))    // South
            || to.equals(from.increase(-1, -1))   // SouthWest
            || to.equals(from.increase(-1, 0))    // West
            || to.equals(from.increase(-1, 1));   // NorthWest
    }

    public boolean isEndOnDiagonalOfStart() {
        return from.isOnDiagonalOf(to);
    }

    public boolean isEndOnStraightLineOfStart() {
        return from.isOnStraightOf(to);
    }

    public boolean isKnightMove() {
        return isEndIncrementOfStartBy(1, 2)
            || isEndIncrementOfStartBy(2, 1)
            || isEndIncrementOfStartBy(2, -1)
            || isEndIncrementOfStartBy(1, -1)
            || isEndIncrementOfStartBy(-1, -1)
            || isEndIncrementOfStartBy(-2, -1)
            || isEndIncrementOfStartBy(-2, 1)
            || isEndIncrementOfStartBy(-1, 1);
    }

    private boolean isEndIncrementOfStartBy(int x, int y) {
        if (from.canIncrease(x, y)) {
            return from.increase(x, y).equals(to);
        }
        return false;
    }

    public boolean isSameTeamPlacedOnEnd() {
        if (!doAnyPieceExistOnEnd()) {
            return false;
        }
        return board.get(to).isSameTeam(board.get(from));
    }

    public boolean doAnyPieceExistInBetween() {
        if (board.get(from).is(PieceType.KNIGHT)) {
            throw new UnsupportedOperationException("나이트에 대해서 이 기능을 지원하지 않습니다.");
        }
        List<Position> path = Position.getPath(from, to);
        path.remove(from);
        path.remove(to);

        return path.stream()
            .anyMatch(board::containsKey);
    }

    public boolean doAnyPieceExistOnEnd() {
        return board.containsKey(to);
    }

    public boolean isStraightMoveBy(int distance) {
        return isEndOnStraightLineOfStart()
            && (from.calculateXDistance(to) == distance
            || from.calculateYDistance(to) == distance);
    }

    public boolean isMoveForward() {
        if (board.get(from).belongs(Team.WHITE)) {
            return to.isYLargerThan(from);
        }
        return from.isYLargerThan(to);
    }

    public boolean isStartOnInitialPosition() {
        if (!board.get(from).is(PieceType.PAWN)) {
            throw new UnsupportedOperationException("해당 기능은 움직일 기물이 폰일 경우에만 지원합니다.");
        }
        if (board.get(from).belongs(Team.WHITE)) {
            return from.isY(Y.TWO);
        }
        return from.isY(Y.SEVEN);
    }

    public boolean isEnemyOnEnd() {
        if (!board.containsKey(to)) {
            return false;
        }
        return !board.get(to).isSameTeam(board.get(from));
    }
}
