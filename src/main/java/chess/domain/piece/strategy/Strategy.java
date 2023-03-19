package chess.domain.piece.strategy;

import chess.domain.board.Square;
import java.util.List;

@FunctionalInterface
public interface Strategy {

    List<Square> findRoute(Square source, Square destination);
}
