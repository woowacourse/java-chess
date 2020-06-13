package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.policy.score.HasMultiplePeerAtFile;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.PiecesState;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.Objects;

public class Piece {

    private final Team team;
    private final String name;
    private final CanNotMoveStrategy canNotMoveStrategy;
    private final CalculateScoreStrategy calculateScoreStrategy;
    public Piece(Team team, String name, CanNotMoveStrategy canNotMoveStrategy, CalculateScoreStrategy calculateScoreStrategy) {
        this.team = team;
        this.name = name;
        this.canNotMoveStrategy = canNotMoveStrategy;
        this.calculateScoreStrategy = calculateScoreStrategy;
    }
    public Team getTeam() {
        return team;
    }

    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return canNotMoveStrategy.canNotMove(from, to, piecesState);
    }

    public Score calculateScore(HasMultiplePeerAtFile hasMultiplePeerAtFile) {
        return calculateScoreStrategy.calculate(hasMultiplePeerAtFile);
    }


    public boolean isSameTeam(Piece toPiece) {
        return team.isSame(toPiece.team);
    }

    public boolean isSameTeam(Team team) {
        return this.team.isSame(team);
    }

    public boolean isOppositeTeam(Piece toPiece) {
        return team.isOpposite(toPiece.team);
    }

    public boolean isPawn() {
        return name.equals(Team.convertName("p", team));
    }

    public boolean isKing() {
        return name.equals(Team.convertName("k", team));
    }

    public String getName() {
        return name;
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
