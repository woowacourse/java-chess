package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.MovingStrategy;
import java.util.List;

public abstract class ChessPiece {
    protected Team team;
    protected final MovingStrategy movingStrategy;
    private final double score;

    ChessPiece(Team team, double score, MovingStrategy movingStrategy) {
        this.team = team;
        this.score = score;
        this.movingStrategy = movingStrategy;
    }

    public boolean isSameTeam(Team other) {
        return this.team == other;
    }

    public double score() {
        return score;
    }

    public abstract List<ChessBoardPosition> getPath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition);

    public abstract boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition);
}
