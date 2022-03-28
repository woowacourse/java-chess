package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.Objects;

public abstract class ChessPiece {
    protected final String name;
    protected final double score;
    protected final Team team;
    protected ChessBoardPosition position;

    protected ChessPiece(String name, double score, Team team, ChessBoardPosition position) {
        this.name = name;
        this.score = score;
        this.team = team;
        this.position = position;
    }

    public abstract boolean isMovable(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen);

    public void move(ChessBoardPosition targetPosition) {
        this.position = targetPosition;
    }

    public boolean isSamePosition(ChessBoardPosition other) {
        return position.equals(other);
    }

    public boolean myTeamExistsInTargetPosition(ChessBoardPosition targetPosition, ChessMen myTeamChessMen) {
        return myTeamChessMen.existChessPieceAt(targetPosition);
    }

    public boolean enemyExistsInTargetPosition(ChessBoardPosition targetPosition, ChessMen enemyChessMen) {
        return enemyChessMen.existChessPieceAt(targetPosition);
    }

    public String getName() {
        return name;
    }

    public ChessBoardPosition getPosition() {
        return position;
    }

    public double getScore() {
        return score;
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
        return Objects.equals(name, that.name) && team == that.team && Objects.equals(position,
                that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, position);
    }
}
