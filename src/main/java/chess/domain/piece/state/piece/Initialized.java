package chess.domain.piece.state.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;

import java.util.List;
import java.util.Objects;

public abstract class Initialized implements Piece {
    protected final String name;
    protected final Team team;
    protected final List<CanNotMoveStrategy> canNotMoveStrategies;
    protected final Position position;
    protected final Score score;
    protected final MoveType moveType;

    protected Initialized(InitializedBuilder builder) {
        this.name = builder.name;
        this.team = builder.team;
        this.canNotMoveStrategies = builder.canNotMoveStrategies;
        this.position = builder.position;
        this.score = builder.score;
        this.moveType = builder.moveType;
    }

    public abstract boolean hasHindrance(Position to, PiecesState piecesState);

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isSameTeam(Team team) {
        return this.team.isSame(team);
    }

    @Override
    public boolean isEnemy(Piece piece) {
        return team.isOpposite(piece.getTeam());
    }

    @Override
    public boolean isKing() {
        return name.equals(PieceType.KING.getName(team));
    }

    @Override
    public boolean attackedKing() {
        return moveType == MoveType.ATTACKED_KING;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean isSameTeam(Piece piece) {
        return team.isSame(piece.getTeam());
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public boolean isHeadingNotForward(Position to) {
        return to.isNotForward(position, team.getForwardDirection());
    }

    public boolean isStayed(Position to) {
        return position.equals(to);
    }

    public Distance calculateDistance(Position to) {
        return position.calculateDistance(to);
    }

    public boolean isHeadingHeadingDiagonal(Position to) {
        return position.isDiagonalDirection(to);
    }

    public boolean isNotHeadingStraightDiagonal(Position to) {
        return position.isNotStraightDiagonalDirection(to);
    }

    public boolean isHeadingVertical(Position to) {
        return position.isVerticalDirection(to);
    }

    public boolean isNotHeadingStraight(Position to) {
        return position.isNotStraightDirection(to);
    }

    public boolean isHeadingStraight(Position to) {
        return position.isStraightDirection(to);
    }

    public abstract static class InitializedBuilder {
        private final String name;
        private final Team team;
        private final List<CanNotMoveStrategy> canNotMoveStrategies;
        private final Position position;
        private final Score score;
        private MoveType moveType;

        public InitializedBuilder(String name,
                                  Position position,
                                  Team team,
                                  List<CanNotMoveStrategy> canNotMoveStrategies,
                                  Score score) {
            this.name = Team.convertName(name, team);
            this.position = position;
            this.team = team;
            this.canNotMoveStrategies = canNotMoveStrategies;
            this.score = score;
            this.moveType = MoveType.INITIALIZED;
        }

        public InitializedBuilder moveType(MoveType moveType) {
            this.moveType = moveType;
            return this;
        }

        public abstract Piece build();
    }

    @Override
    public boolean canNotMove(Position to, PiecesState piecesState) {
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(position, to, piecesState));
    }

    protected boolean hasHindranceStraightInBetween(Position to, PiecesState piecesState) {
        if (position.isDiagonalDirection(to)) {
            return hasHindranceDiagonallyInBetween(to, piecesState);
        }

        return hasHindrancePerpendicularlyInBetween(to, piecesState);

    }

    protected boolean hasHindranceDiagonallyInBetween(Position to, PiecesState piecesState) {
        Distance amount = position.calculateHorizontalDistance(to);
        Direction direction = position.calculateDirection(to);
        return hasHindranceInBetween(piecesState, amount, direction, position);
    }

    protected boolean hasHindrancePerpendicularlyInBetween(Position to, PiecesState piecesState) {
        Distance amount = position.calculateDistance(to);
        Direction direction = position.calculateDirection(to);
        return hasHindranceInBetween(piecesState, amount, direction, position);
    }

    private boolean hasHindranceInBetween(PiecesState piecesState, Distance amount, Direction direction, Position targetPosition) {
        for (int i = Position.FORWARD_AMOUNT; i < amount.getValue(); i++) {
            targetPosition = targetPosition.go(direction);
            Piece piece = piecesState.getPiece(targetPosition);
            if (piece.isNotBlank()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Initialized that = (Initialized) o;
        return Objects.equals(canNotMoveStrategies, that.canNotMoveStrategies) &&
                Objects.equals(position, that.position) &&
                Objects.equals(score, that.score) &&
                moveType == that.moveType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(canNotMoveStrategies, position, score, moveType);
    }
}
