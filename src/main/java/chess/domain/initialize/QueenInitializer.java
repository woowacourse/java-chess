package chess.domain.initialize;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.*;

public final class QueenInitializer implements InitializeStrategy {
    @Override
    public Map<Position, PieceType> initialize() {
        Map<Position, PieceType> piece = new HashMap<>();
        piece.put(Position.of("d8"), PieceType.QUEEN);
        piece.put(Position.of("d1"), PieceType.QUEEN);

        return Collections.unmodifiableMap(piece);
    }
}