package chess.model.piece;

import chess.model.Position;
import chess.model.Team;
import chess.model.Turn;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Piece implements Comparable<Piece> {
    protected Position position;
    protected final Team team;

    protected Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public final boolean isLastFile() {
        return position.isLastFile();
    }

    public final boolean findPosition(Position position) {
        return this.position.equals(position);
    }

    public final void moveTo(Piece targetPiece) {
        position = targetPiece.position;
    }

    public final boolean isSameTeam(Piece targetPiece) {
        return team.equals(targetPiece.team);
    }

    public final boolean isOtherTeam(Piece targetPiece) {
        return team.getForwardDirection() + targetPiece.team.getForwardDirection() == 0;
    }

    public final int getHorizontalDistance(Piece other) {
        return this.position.getFile().absMinus(other.position.getFile());
    }

    public final int getVerticalDistance(Piece other) {
        return this.position.getRank().absMinus(other.position.getRank());
    }

    public final Position getPositiveDiagonalPosition(int distance) {
        return this.position.getPositiveDiagonalPosition(distance);
    }

    public final Position getNegativeDiagonalPosition(int distance) {
        return this.position.getNegativeDiagonalPosition(distance);
    }

    public final Position getRightHorizontalPosition(int distance) {
        return this.position.getRightHorizontalPosition(distance);
    }

    public final Position getUpVerticalPosition(int distance) {
        return this.position.getUpVerticalPosition(distance);
    }

    public final boolean isCurrentTurn(Turn turn) {
        return turn.isCurrentTeam(team);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKill(Piece piece) {
        return false;
    }

    public abstract double getScore();

    public abstract List<Position> getIntervalPosition(Piece targetPiece);

    public List<Position> getIntervalPosition(Position source, Position target) {
        return Collections.emptyList(); // abstract로 변경 필요
    }

    public boolean isMovable(Position source, Position target) {return false;} // abstract로 변경 필요

    public abstract String getName();

    @Override
    public int compareTo(Piece piece) {
        if (this.position.isLessRankThan(piece.position)) {
            return 1;
        }
        if (isFileComparison(piece)) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position) && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, team);
    }

    private boolean isFileComparison(Piece piece) {
        return this.position.getRank() == piece.position.getRank() && this.position.isBiggerFileThan(piece.position);
    }
}
