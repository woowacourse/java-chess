package chess.domain.board;

import chess.domain.piece.unit.Piece;
import chess.domain.position.Position;
import java.util.Map;

@FunctionalInterface
public interface BoardGenerator {

    Map<Position, Piece> generate();
}
