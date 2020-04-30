package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.List;

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

    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        List<CanNotMoveStrategy> canNotMoveStrategies = constituteStrategies(from);
        return canNotMoveStrategies.stream()
                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(from, to, piecesState));
    }

    protected abstract Score calculateScore(PiecesState piecesState);

    protected abstract List<CanNotMoveStrategy> constituteStrategies(Position from);


    boolean isSameTeam(Piece toPiece) {
        //todo: getter??
        return team.isSame(toPiece.team);
    }

    boolean isSameTeam(Team team) {
        return this.team.isSame(team);
    }

    boolean isOppositeTeam(Piece toPiece) {
        return team.isOpposite(toPiece.team);
    }

    @Override
    public String toString() {
        return name;
    }
}
