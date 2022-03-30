package chess.domain.generator;

import chess.domain.piece.Piece;
import java.util.List;

@FunctionalInterface
public interface BoardGenerator {

    List<List<Piece>> generate();
}
