package chess.domain.board;

import chess.domain.position.Coordinate;
import java.util.List;
import java.util.stream.Stream;

public interface Path2 {

    public List<Coordinate> coordinates();

    Stream<Coordinate> stream();

    boolean contains(Coordinate coordinate);
}
