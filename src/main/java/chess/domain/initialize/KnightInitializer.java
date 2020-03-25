package chess.domain.initialize;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.*;

public final class KnightInitializer implements InitializeStrategy {
    @Override
    public Map<Position, PieceType> initialize() {
        Map<Position, PieceType> piece = new HashMap<>();
        piece.put(Position.of("b8"), PieceType.KNIGHT);
        piece.put(Position.of("b1"), PieceType.KNIGHT);
        piece.put(Position.of("g8"), PieceType.KNIGHT);
        piece.put(Position.of("g1"), PieceType.KNIGHT);

        return Collections.unmodifiableMap(piece);
    }
}