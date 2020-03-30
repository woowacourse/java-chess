package chess.piece.type;

import java.util.Map;
import java.util.Objects;

import chess.location.Location;
import chess.piece.type.movable.PieceMovable;
import chess.score.Score;
import chess.team.Team;

public abstract class Piece {
    private static final char BLACk_KING_VALUE = 'K';
    private static final char WHITE_KING_VALUE = 'k';

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

    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
        return pieceMovable.canMove(board, now, after);
    }

    public boolean isSameTeam(Team team) {
        return isBlack() == team;
    }

    public boolean isNotSame(Team team) {
        return isBlack() != team;
    }

    public boolean isNotSameTeam(Piece piece) {
        return isBlack() != piece.isBlack();
    }

    public boolean isKing() {
        return this.name == BLACk_KING_VALUE || this.name == WHITE_KING_VALUE;
    }

    private Team isBlack() {
        if (Character.isUpperCase(name)) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public boolean isReverseTeam(Team team) {
        return team.isReverseTeam(isBlack());
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
