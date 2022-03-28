package chess.piece;

import chess.Position;

import java.util.List;

public interface RookMovable {

    default List<Position> getHorizontalPositions(List<Position> positions, List<Piece> list) {
        Piece left = list.get(0);
        Piece right = list.get(1);
        int distance = right.getHorizontalDistance(left);
        for (int i = 1; i < distance; i++) {
            positions.add(left.getRightHorizontalPosition(i));
        }
        return positions;
    }

    default List<Position> getVerticalPositions(List<Position> positions, List<Piece> list) {
        Piece up = list.get(0);
        Piece down = list.get(1);
        int distance = up.getVerticalDistance(down);
        for (int i = 1; i < distance; i++) {
            positions.add(down.getUpVerticalPosition(i));
        }
        return positions;
    }
}
