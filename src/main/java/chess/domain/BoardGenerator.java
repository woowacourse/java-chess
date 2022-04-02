package chess.domain;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Square;

public interface BoardGenerator {

    Map<Square, Piece> generate();
}
