package chess.domain.board.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface BoardGenerationStrategy {

    Map<Position, Piece> create();
}
