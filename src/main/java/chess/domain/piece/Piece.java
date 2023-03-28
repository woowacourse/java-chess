package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.MoveErrorMessage;
import chess.domain.Path;
import chess.domain.Position;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    protected Camp camp;
    protected PieceType type;

    public Piece(final Camp camp, final PieceType type) {
        this.camp = camp;
        this.type = type;
    }

    public void validateMove(Position source, Position destination, Map<Position, Piece> piecesByPosition) {
        CheckablePaths checkablePaths = findCheckablePaths(source);
        Path path = checkablePaths.findPathContainingPosition(destination);
        if (path.hasObstacleAtAnyStepPositions(source, destination, piecesByPosition)) {
            throw new IllegalArgumentException(MoveErrorMessage.OBSTACLE_IN_PATH_ERROR_MESSAGE);
        }
        Piece target = piecesByPosition.get(destination);
        validateMoveByPieceRule(source, destination, target);
    }

    private void validateMoveByPieceRule(Position source, Position destination, Piece target) {
        if (target.getType() == PieceType.EMPTY) {
            validateMoveToEmpty(source, destination);
            return;
        }
        validateAttack(source, destination, target);
    }

    private void validateMoveToEmpty(Position source, Position destination) {
        if (canMoveToEmpty(source, destination)) {
            return;
        }
        throw new IllegalArgumentException(MoveErrorMessage.WRONG_DESTINATION_ERROR_MESSAGE);
    }

    private void validateAttack(Position source, Position destination, Piece target) {
        if (canAttack(source, destination, target)) {
            return;
        }
        throw new IllegalArgumentException(MoveErrorMessage.WRONG_ATTACK_ERROR_MESSAGE);
    }

    protected abstract CheckablePaths findCheckablePaths(Position current);

    protected abstract boolean canMoveToEmpty(Position source, Position dest);

    protected abstract boolean canAttack(Position source, Position dest, Piece target);

    public abstract double sumPointsOf(List<Position> existingPositions);

    public boolean isDifferentCamp(Camp other) {
        return this.camp != other;
    }

    public boolean isSameCamp(final Camp camp) {
        return this.camp == camp;
    }

    public Camp getCamp() {
        return camp;
    }

    // TODO isSameType 으로 변경
    public PieceType getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return camp == piece.camp && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, type);
    }

}
