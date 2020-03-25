package chess.domain.initialize;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Map;

public interface InitializeStrategy {
    Map<Position, PieceType> initialize();
}
