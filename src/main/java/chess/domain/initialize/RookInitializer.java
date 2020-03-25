package chess.domain.initialize;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.*;

public final class RookInitializer implements InitializeStrategy {
    @Override
    public Map<Position, PieceType> initialize() {
        Map<Position, PieceType> piece = new HashMap<>();
        piece.put(Position.of("a8"), PieceType.ROOK);
        piece.put(Position.of("a1"), PieceType.ROOK);
        piece.put(Position.of("h8"), PieceType.ROOK);
        piece.put(Position.of("h1"), PieceType.ROOK);

        return Collections.unmodifiableMap(piece);
    }
}