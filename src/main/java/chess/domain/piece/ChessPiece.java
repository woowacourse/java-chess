package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;

public abstract class ChessPiece {
    protected Team team;
    private double score;

    ChessPiece(Team team, double score) {
        this.team = team;
        this.score = score;
    }

    public abstract List<ChessBoard> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition);

    public abstract ChessPiece hardCopy();

    public boolean isSameTeam(Team other) {
        return this.team == other;
    }
}
