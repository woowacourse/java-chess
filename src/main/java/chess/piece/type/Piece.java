package chess.piece.type;

import java.util.Map;

import chess.board.Location;
import chess.team.Team;

public abstract class Piece {
    protected final char name;

    Piece(char name) {
        this.name = name;
    }

    public abstract boolean canMove(Location now, Location after);

    public boolean isSameTeam(boolean black) {
        return isBlack() == black;
    }

    public boolean isSameTeam(Piece piece) {
        return isBlack() == piece.isBlack();
    }

    public boolean is(Piece piece) {
        return piece.getClass().equals(this.getClass());
    }

    protected boolean isBlack() {
        return Character.isUpperCase(name);
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
            System.out.println(nowLocation);
            if (board.containsKey(nowLocation)) {
                return true;
            }

        }

        return false;
    }
}
