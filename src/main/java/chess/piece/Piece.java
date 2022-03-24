package chess.piece;

import chess.Position;
import chess.Team;

import java.util.Objects;

public abstract class Piece implements Comparable<Piece> {
    protected final Position position;
    protected final Team team;

    protected Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract boolean isMovable(Position position);

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

    private boolean isFileComparison(Piece piece) {
        return this.position.getRank() == piece.position.getRank() && this.position.isBiggerFileThan(piece.position);
    }

    public abstract String getName();

    public boolean isLastFile() {
        return position.isLastFile();
    }

    public boolean findPosition(Position position) {
        return this.position.equals(position);
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
