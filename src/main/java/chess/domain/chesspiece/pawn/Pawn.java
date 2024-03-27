package chess.domain.chesspiece.pawn;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Team;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Pawn extends Piece {

    public Pawn(Team team) {
        super(team);
    }

    protected abstract int calculatePawnRankDistance(Position source, Position target);

    protected abstract boolean isStartPosition(Position source);

    @Override
    public List<Position> findRoute(Position source, Position target, Piece targetPiece) {
        if(targetPiece.isEmpty()) {
            return findMovingRoute(source, target);
        }

        return findAttackRoute(source, target);
    }

    private List<Position> findMovingRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);

        if (canMoveForwardTwice(source, target)) {
            Direction direction = Direction.findDirection(source, target);
            route.add(source.move(direction));
        }

        return Collections.unmodifiableList(route);
    }

    private List<Position> findAttackRoute(Position source, Position target) {
        validateAttackRule(source, target);
        return Collections.unmodifiableList(new ArrayList<>());
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private boolean isMovable(Position source, Position target) {
        return canMoveForwardTwice(source, target)
                || canMoveForwardOnce(source, target);
    }

    private boolean canMoveForwardTwice(Position source, Position target) {
        int columnDistance = calculatePawnRankDistance(source, target);
        return isStartPosition(source) && source.isSameFile(target) && columnDistance == 2;
    }

    private boolean canMoveForwardOnce(Position source, Position target) {
        int columnDistance = calculatePawnRankDistance(source, target);
        return source.isSameFile(target) && columnDistance == 1;
    }

    private void validateAttackRule(Position source, Position target) {
        if (!canAttack(source, target)) {
            throw new IllegalArgumentException("공격할 수 없는 곳입니다.");
        }
    }

    private boolean canAttack(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int colDistance = calculatePawnRankDistance(source, target);
        return fileDistance == 1 && colDistance == 1;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
