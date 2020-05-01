package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.Pieces;
import chess.domain.piece.team.Team;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final Team team;
    private final String name;

    protected Piece(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public boolean canNotMove(Position from, Position to, Pieces pieces) {
        List<CanNotMoveStrategy> canNotMoveStrategies = constituteStrategies(from);
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(from, to, pieces));
    }

    public abstract Score calculateScore(Pieces pieces);

    protected abstract List<CanNotMoveStrategy> constituteStrategies(Position from);


    public boolean isSameTeam(Piece toPiece) {
        //todo: getter??
        return team.isSame(toPiece.team);
    }

    public boolean isSameTeam(Team team) {
        return this.team.isSame(team);
    }

    public boolean isOppositeTeam(Piece toPiece) {
        return team.isOpposite(toPiece.team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team &&
                Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
