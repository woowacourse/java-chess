package chess.board.piece;

import chess.board.Team;
import chess.board.Turn;
import chess.board.piece.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece implements Comparable<Piece> {
    protected Position position;
    protected final Team team;

    protected Piece(Team team) {
        this.team = team;
    }

    protected Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    private boolean isFileComparison(Piece piece) {
        return this.position.getRank() == piece.position.getRank() && this.position.isBiggerFileThan(piece.position);
    }

    public boolean isLastFile() {
        return position.isLastFile();
    }

    public boolean findPosition(Position position) {
        return this.position.equals(position);
    }

    public void moveTo(Position targetPosition) {
        position = targetPosition;
    }

    public boolean isSameTeam(Piece targetPiece) {
        return team.equals(targetPiece.team);
    }

    public boolean isOtherTeam(Piece targetPiece) {
        return team.getForwardDirection() + targetPiece.team.getForwardDirection() == 0;
    }

    public int getHorizontalDistance(Piece other) {
        return this.position.getFile().absMinus(other.position.getFile());
    }

    public int getVerticalDistance(Piece other) {
        return this.position.getRank().absMinus(other.position.getRank());
    }

    public Position getPositiveDiagonalPosition(int distance) {
        return this.position.getPositiveDiagonalPosition(distance);
    }

    public Position getNegativeDiagonalPosition(int distance) {
        return this.position.getNegativeDiagonalPosition(distance);
    }

    public Position getRightHorizontalPosition(int distance) {
        return this.position.getRightHorizontalPosition(distance);
    }

    public Position getUpVerticalPosition(int distance) {
        return this.position.getUpVerticalPosition(distance);
    }

    public boolean isKill(Piece piece) {
        return false;
    }

    public boolean isNotCurrentTurn(Turn turn) {
        return turn.isNotCurrentTurn(team);
    }

    public abstract double getScore();

    public abstract List<Position> getIntervalPosition(Piece targetPiece);

    public abstract boolean isMovableRange(Position position);

    public abstract String getName();

    public abstract String getType();

    public Position getPosition() {
        return position;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public Team getTeam() {
        return team;
    }

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
}
