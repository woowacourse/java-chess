package chess.domain.board;

import chess.domain.position.Notation;
import java.util.List;
import java.util.stream.Stream;

public interface Path2 {

    List<Notation> coordinates();

    boolean contains(final Notation notation);

    Stream<Notation> stream();

}
