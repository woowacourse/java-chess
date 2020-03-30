package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Distance;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Initialized extends Started {
    protected final List<CanNotMoveStrategy> canNotMoveStrategies;
    protected final Position position;

    protected Initialized(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, team);
        this.position = position;
        this.canNotMoveStrategies = canNotMoveStrategies;
    }

    public abstract boolean hasHindrance(Position to, Board board);

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    public boolean isHeadingNotForward(Position to) {
        return to.isNotForward(position, team.getForwardDirection());
    }

    public boolean isStayed(Position to) {
        return position.equals(to);
    }

    public boolean isSameTeam(Piece piece) {
        return team.isSame(piece.getTeam());
    }

    public Distance calculateDistance(Position to) {
        return position.calculateDistance(to);
    }

    public boolean isHeadingHeadingDiagonal(Position to) {
        return position.isDiagonalDirection(to);
    }

    public boolean isNotHeadingStraightDiagonal(Position to) {
        return position.isNotStraightDiagonalDirection(to);
    };

    public boolean isHeadingPerpendicular(Position to) {
        return position.isPerpendicularDirection(to);
    }

    public boolean isNotHeadingStraight(Position to) {
        return position.isNotStraightDirection(to);
    }

    protected boolean canNotMove(Position to, Board board) {
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(this, to, board));
    }

    protected boolean hasHindranceDiagonallyInBetween(Position to, Board board) {
        Distance amount = position.calculateHorizontalDistance(to);
        Direction direction = position.calculateDirection(to);
        return hasHindranceInBetween(board, amount, direction, position);
    }

    protected boolean hasHindrancePerpendicularlyInBetween(Position to, Board board) {
        Distance amount = position.calculateDistance(to);
        Direction direction = position.calculateDirection(to);
        return hasHindranceInBetween(board, amount, direction, position);
    }

    private boolean hasHindranceInBetween(Board board, Distance amount, Direction direction, Position targetPosition) {
        for (int i = Position.FORWARD_AMOUNT; i < amount.getValue(); i++) {
            targetPosition = targetPosition.go(direction);
            Piece piece = board.getPiece(targetPosition);
            if (piece.isNotBlank()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Initialized that = (Initialized) o;
        return Objects.equals(canNotMoveStrategies, that.canNotMoveStrategies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(canNotMoveStrategies);
    }

    public boolean isHeadingStraight(Position to) {
        return position.isStraightDirection(to);
    }
}
