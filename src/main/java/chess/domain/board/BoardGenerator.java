package chess.domain.board;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Square;

@FunctionalInterface
public interface BoardGenerator {

    Map<Square, Piece> generate();
}
