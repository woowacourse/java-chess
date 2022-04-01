package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.Objects;

public abstract class ChessPiece {
    protected final double score;
    protected final Team team;

    protected ChessPiece(double score, Team team) {
        this.score = score;
        this.team = team;
    }

    public abstract boolean isMovable(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                               ChessBoard chessBoard);

    public boolean isSameTeam(Team otherTeam) {
        return team.isSame(otherTeam);
    }

    public double getScore() {
        return score;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return Double.compare(that.score, score) == 0 && team == that.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, team);
    }
}
