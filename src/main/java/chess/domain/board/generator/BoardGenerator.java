package chess.domain.board.generator;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface BoardGenerator {
    Map<Position, Piece> create();
}
