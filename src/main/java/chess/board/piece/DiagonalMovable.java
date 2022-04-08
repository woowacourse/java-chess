package chess.board.piece;

import chess.board.piece.position.Position;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface DiagonalMovable {

    default List<Position> getPositiveDiagonal(List<Piece> list) {
        Piece rightUpper = list.get(0);
        Piece leftUnder = list.get(1);
        int distance = rightUpper.getVerticalDistance(leftUnder);
        return IntStream.range(1, distance)
                .mapToObj(leftUnder::getPositiveDiagonalPosition)
                .collect(Collectors.toList());
    }

    default List<Position> getNegativeDiagonal(List<Piece> list) {
        Piece leftUpper = list.get(0);
        Piece rightUnder = list.get(1);
        int distance = rightUnder.getVerticalDistance(leftUpper);
        return IntStream.range(1, distance)
                .mapToObj(leftUpper::getNegativeDiagonalPosition)
                .collect(Collectors.toList());
    }
}
