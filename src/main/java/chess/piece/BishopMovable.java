package chess.piece;

import chess.Position;

import java.util.List;

public interface BishopMovable {

    default List<Position> getPositiveDiagonal(List<Position> positions, List<Piece> list) {
        Piece rightUpper = list.get(0);
        Piece leftUnder = list.get(1);

        int distance = rightUpper.getVerticalDistance(leftUnder);
        for (int i = 1; i < distance; i++) {
            positions.add(leftUnder.getPositiveDiagonalPosition(i));
        }
        return positions;
    }

    default List<Position> getNegativeDiagonal(List<Position> positions, List<Piece> list) {
        Piece leftUpper = list.get(0);
        Piece rightUnder = list.get(1);
        int distance = rightUnder.getVerticalDistance(leftUpper);
        for (int i = 1; i < distance; i++) {
            positions.add(leftUpper.getNegativeDiagonalPosition(i));
        }
        return positions;
    }
}
