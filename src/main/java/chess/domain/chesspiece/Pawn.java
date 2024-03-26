package chess.domain.chesspiece;

import chess.domain.position.Rank;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chesspiece.Role.*;
import static chess.domain.chesspiece.Team.WHITE;

public class Pawn extends Piece {
    private static Rank WHITE_PAWN_START_COLUMN = Rank.TWO;
    private static Rank BLACK_PAWN_START_COLUMN = Rank.SEVEN;

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
        return canMoveForwardTwice(source, target)
                || canMoveForwardOnce(source, target)
                || canAttack(source, target);
    }

    private boolean canMoveForwardTwice(Position source, Position target) {
        int columnDistance = calculatePawnRankDistance(source, target);
        return isStartPosition(source) && source.isSameFile(target) && columnDistance == 2;
    }

    private int calculatePawnRankDistance(Position source, Position target) {
        int columnDistance = source.subtractRanks(target);
        if (getTeam() == WHITE) {
            columnDistance *= -1;
        }
        return columnDistance;
    }

    private boolean isStartPosition(Position source) {
        if (getTeam() == WHITE) {
            return source.getRank() == WHITE_PAWN_START_COLUMN;
        }
        return source.getRank() == BLACK_PAWN_START_COLUMN;
    }

    private boolean canMoveForwardOnce(Position source, Position target) {
        int columnDistance = calculatePawnRankDistance(source, target);
        return source.isSameFile(target) && columnDistance == 1;
    }

    private boolean canAttack(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int colDistance = calculatePawnRankDistance(source, target);
        return fileDistance == 1 && colDistance == 1;
    }

    @Override
    public Role getRole() {
        if (getTeam().isWhite()) {
            return WHITE_PAWN;
        }
        return BLACK_PAWN;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
