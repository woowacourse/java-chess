package chess.domain.initialize;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.*;

public final class PawnInitializer implements InitializeStrategy {
    @Override
    public Map<Position, PieceType> initialize() {
        Map<Position, PieceType> piece = new HashMap<>();
        piece.put(Position.of("a7"), PieceType.PAWN);
        piece.put(Position.of("b7"), PieceType.PAWN);
        piece.put(Position.of("c7"), PieceType.PAWN);
        piece.put(Position.of("d7"), PieceType.PAWN);
        piece.put(Position.of("e7"), PieceType.PAWN);
        piece.put(Position.of("f7"), PieceType.PAWN);
        piece.put(Position.of("g7"), PieceType.PAWN);
        piece.put(Position.of("h7"), PieceType.PAWN);
        piece.put(Position.of("a2"), PieceType.PAWN);
        piece.put(Position.of("b2"), PieceType.PAWN);
        piece.put(Position.of("c2"), PieceType.PAWN);
        piece.put(Position.of("d2"), PieceType.PAWN);
        piece.put(Position.of("e2"), PieceType.PAWN);
        piece.put(Position.of("f2"), PieceType.PAWN);
        piece.put(Position.of("g2"), PieceType.PAWN);
        piece.put(Position.of("h2"), PieceType.PAWN);

        return Collections.unmodifiableMap(piece);
    }
}
