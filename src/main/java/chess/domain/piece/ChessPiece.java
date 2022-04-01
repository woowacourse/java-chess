package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.strategy.MovingStrategy;
import java.util.List;

public abstract class ChessPiece {
    protected Team team;
    protected MovingStrategy movingStrategy;
    private double score;

    ChessPiece(Team team, double score, MovingStrategy movingStrategy) {
        this.team = team;
        this.score = score;
        this.movingStrategy = movingStrategy;
    }

    public abstract List<ChessBoardPosition> getPath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition);

    public abstract ChessPiece hardCopy();

    public boolean isSameTeam(Team other) {
        return this.team == other;
    }
}
