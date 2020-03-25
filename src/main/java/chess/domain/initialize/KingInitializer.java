package chess.domain.initialize;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.*;

public final class KingInitializer implements InitializeStrategy {
    @Override
    public Map<Position, PieceType> initialize() {
        Map<Position, PieceType> piece = new HashMap<>();
        piece.put(Position.of("e8"), PieceType.KING);
        piece.put(Position.of("e1"), PieceType.KING);

        return Collections.unmodifiableMap(piece);
    }
}
