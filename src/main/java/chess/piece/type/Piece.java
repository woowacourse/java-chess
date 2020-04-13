package chess.piece.type;

import java.util.Objects;

import chess.board.Route;
import chess.piece.type.movable.PieceMovable;
import chess.score.Score;
import chess.team.Team;

public abstract class Piece {
    public final char name;
    protected final Score score;
    private final PieceMovable pieceMovable;

    Piece(char name, Score score, Team team, PieceMovable pieceMovable) {
        this.name = changeName(team, name);
        this.score = score;
        this.pieceMovable = pieceMovable;
    }

    private static char changeName(Team team, char name) {
        if (team.isBlack()) {
            return Character.toUpperCase(name);
        }
        return name;
    }

    public boolean canMove(Route route) {
        return pieceMovable.canMove(route);
    }

    public boolean isSameTeam(Team team) {
        return getTeam() == team;
    }

    public boolean isNotSame(Team team) {
        return getTeam() != team;
    }

    public boolean isNotSameTeam(Piece piece) {
        return getTeam() != piece.getTeam();
    }

    public boolean isKing() {
        return this.getClass() == King.class;
    }

    public boolean isReverseTeam(Piece piece) {
        Team anotherTeam = piece.getTeam();
        Team myTeam = this.getTeam();
        return myTeam.isReverseTeam(anotherTeam);
    }

    public Team getTeam() {
        if (Character.isUpperCase(name)) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public char getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return name == piece.name &&
                Objects.equals(score, piece.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
