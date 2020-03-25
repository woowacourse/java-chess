package chess.domain.initialize;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.*;

public final class BishopInitializer implements InitializeStrategy {
    @Override
    public Map<Position, PieceType> initialize() {
        Map<Position, PieceType> piece = new HashMap<>();
        piece.put(Position.of("c8"), PieceType.BISHOP);
        piece.put(Position.of("c1"), PieceType.BISHOP);
        piece.put(Position.of("f8"), PieceType.BISHOP);
        piece.put(Position.of("f1"), PieceType.BISHOP);

        return Collections.unmodifiableMap(piece);
    }
}