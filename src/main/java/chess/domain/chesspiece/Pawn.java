package chess.domain.chesspiece;

import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chesspiece.Role.*;
import static chess.domain.chesspiece.Team.WHITE;

public class Pawn extends Piece {
    private static Column WHITE_PAWN_START_COLUMN = Column.valueOf("2");
    private static Column BLACK_PAWN_START_COLUMN = Column.valueOf("7");

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public List<Position> getRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);

        Direction direction = Direction.findDirection(source, target);
        Position movingPosition = direction.move(source);

        while (!movingPosition.equals(target)) {
            route.add(movingPosition);
            movingPosition = direction.move(movingPosition);
        }
        return Collections.unmodifiableList(route);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private boolean isMovable(Position source, Position target) {
        return moveForwardTwice(source, target)
                || moveForward(source, target)
                || attack(source, target);
    }

    private boolean moveForwardTwice(Position source, Position target) {
        int columnDistance = calculatePawnColumnDistance(source, target);
        return isStartPosition(source) && source.isSameRow(target) && columnDistance == 2;
    }

    private int calculatePawnColumnDistance(Position source, Position target) {
        int columnDistance = source.subtractColumns(target);
        if(team == WHITE) {
            columnDistance *= -1;
        }
        return columnDistance;
    }

    private boolean isStartPosition(Position source) {
        if(team == WHITE) {
            return source.getColumn() == WHITE_PAWN_START_COLUMN;
        }
        return source.getColumn() == BLACK_PAWN_START_COLUMN;
    }

    private boolean moveForward(Position source, Position target) {
        int columnDistance = calculatePawnColumnDistance(source, target);
        return source.isSameRow(target) && columnDistance == 1;
    }

    private boolean attack(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int colDistance = calculatePawnColumnDistance(source, target);
        return rowDistance == 1 && colDistance == 1;
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_PAWN;
        }
        return BLACK_PAWN;
    }
}
