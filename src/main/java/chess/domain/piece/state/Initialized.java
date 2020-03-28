package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.position.Distance;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Initialized extends Started {
    //todo: change to private
    protected final List<CanNotMoveStrategy> canNotMoveStrategies;

    protected Initialized(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team);
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

    public boolean isHeadingDiagonal(Position to) {
        return position.isDiagonalDirection(to);
    }

    protected boolean canNotMove(Position to, Board board) {
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(this, to, board));
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
}
