package chess.domain.generator;

import chess.domain.Board;

@FunctionalInterface
public interface BoardGenerator {

    Board generate();
}
