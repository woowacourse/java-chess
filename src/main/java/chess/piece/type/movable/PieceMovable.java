package chess.piece.type.movable;

import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;

public interface PieceMovable {
    boolean canMove(Map<Location, Piece> board, Location now, Location after);

    default boolean hasNotObstacle(Map<Location, Piece> board, Location now, Location after) {
        int weight = 1;
        Location nowLocation = now.calculateNextLocation(after, weight);
        System.out.println(!nowLocation.equals(after));
        while (!nowLocation.equals(after)) {
            if (board.containsKey(nowLocation)) {
                return false;
            }
            weight++;
            nowLocation = now.calculateNextLocation(after, weight);
        }
        return true;
    }
}
