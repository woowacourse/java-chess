package chess.piece.type;

import java.util.Map;

import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

public abstract class Piece {
    protected final char name;
    protected final Score score;

    Piece(char name, Score score) {
        this.name = name;
        this.score = score;
    }

    public abstract boolean canMove(Location now, Location after);

    public boolean isSameTeam(Team team) {
        return isSameTeam(team.isBlack());
    }

    public boolean isSameTeam(boolean black) {
        return isBlack() == black;
    }

    public boolean isSameTeam(Piece piece) {
        return isBlack() == piece.isBlack();
    }

    public boolean isSameType(Piece piece) {
        return piece.getClass().equals(this.getClass());
    }

    public boolean isKing() {
        return this.name == 'K' || this.name == 'k';
    }

    protected boolean isBlack() {
        return Character.isUpperCase(name);
    }

    public char getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }

    public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
        Location nowLocation = new Location(now);

        for (int weight = 1; ; weight++) {
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
}
