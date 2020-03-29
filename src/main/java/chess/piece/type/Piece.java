package chess.piece.type;

import java.util.Map;
import java.util.Objects;

import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

public abstract class Piece {
    private static final char BLACk_KING_VALUE = 'K';
    private static final char WHITE_KING_VALUE = 'k';

    public final char name;
    protected final Score score;

    Piece(char name, Score score) {
        this.name = name;
        this.score = score;
    }

    public abstract boolean canMove(Location now, Location after);

    public boolean isSameTeam(Team team) {
        return isBlack() == team.isBlack();
    }

    public boolean isSameTeam(boolean black) {
        return isBlack() == black;
    }

    public boolean isSameTeam(Piece piece) {
        return isBlack() == piece.isBlack();
    }

    public boolean isKing() {
        return this.name == BLACk_KING_VALUE || this.name == WHITE_KING_VALUE;
    }

    protected boolean isBlack() {
        return Character.isUpperCase(name);
    }

    public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
        Location nowLocation;
        for (int weight = 1;; weight++) {
            nowLocation = now.calculateNextLocation(destination, weight);
            if (nowLocation.equals(destination)) {
                break;
            }
            if (board.containsKey(nowLocation)) {
                return true;
            }
        }
        return false;
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
