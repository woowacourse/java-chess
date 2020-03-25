package chess.domain.initialize;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public interface InitializeStrategy {
    Map<Position, Piece> initialize();
}
