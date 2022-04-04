package chess.board.piece;

import chess.board.piece.position.Position;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface HorizontalAndVerticalMovable {

    default List<Position> getHorizontalPositions(List<Piece> list) {
        Piece left = list.get(0);
        Piece right = list.get(1);
        int distance = right.getHorizontalDistance(left);
        return IntStream.range(1, distance)
                .mapToObj(left::getRightHorizontalPosition)
                .collect(Collectors.toList());
    }

    default List<Position> getVerticalPositions(List<Piece> list) {
        Piece up = list.get(0);
        Piece down = list.get(1);
        int distance = up.getVerticalDistance(down);
        return IntStream.range(1, distance)
                .mapToObj(down::getUpVerticalPosition)
                .collect(Collectors.toList());
    }
}
