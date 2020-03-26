package chess.domain.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PawnInitializer implements InitializeStrategy {
    @Override
    public Map<Position, Piece> initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of("a7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Position.of("b7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Position.of("c7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Position.of("d7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Position.of("e7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Position.of("f7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Position.of("g7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Position.of("h7"), new Piece(PieceType.PAWN, Team.BLACK));

        pieces.put(Position.of("a2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Position.of("b2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Position.of("c2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Position.of("d2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Position.of("e2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Position.of("f2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Position.of("g2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Position.of("h2"), new Piece(PieceType.PAWN, Team.WHITE));

        return Collections.unmodifiableMap(pieces);
    }
}
