package chess.domain.piece.state.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Direction;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;

import java.util.List;
import java.util.Objects;

public abstract class Initialized implements Piece {
    protected final String name;
    protected final Team team;
    protected final List<CanNotMoveStrategy> canNotMoveStrategies;
    protected final Score score;
    protected final MoveType moveType;

    protected Initialized(InitializedBuilder builder) {
        this.name = builder.name;
        this.team = builder.team;
        this.canNotMoveStrategies = builder.canNotMoveStrategies;
        this.score = builder.score;
        this.moveType = builder.moveType;
    }

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
        //todo: refac
        return false;
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
    public Direction getForwardDirection() {
        return team.getForwardDirection();
    }

    @Override
    public boolean isSameTeam(Piece piece) {
        return team.isSame(piece.getTeam());
    }

    public abstract static class InitializedBuilder {
        private final String name;
        private final Team team;
        private final List<CanNotMoveStrategy> canNotMoveStrategies;
        private final Score score;
        private MoveType moveType;

        public InitializedBuilder(String name,
                                  Team team,
                                  List<CanNotMoveStrategy> canNotMoveStrategies,
                                  Score score) {
            this.name = Team.convertName(name, team);
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
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Initialized that = (Initialized) o;
        return Objects.equals(name, that.name) &&
                team == that.team &&
                Objects.equals(canNotMoveStrategies, that.canNotMoveStrategies) &&
                Objects.equals(score, that.score) &&
                moveType == that.moveType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, canNotMoveStrategies, score, moveType);
    }
}
