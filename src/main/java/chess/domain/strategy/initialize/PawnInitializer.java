package chess.domain.strategy.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PawnInitializer implements InitializeStrategy {
    @Override
    public Map<Position, Piece> initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Positions.of("a7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Positions.of("b7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Positions.of("c7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Positions.of("d7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Positions.of("e7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Positions.of("f7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Positions.of("g7"), new Piece(PieceType.PAWN, Team.BLACK));
        pieces.put(Positions.of("h7"), new Piece(PieceType.PAWN, Team.BLACK));

        pieces.put(Positions.of("a2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Positions.of("b2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Positions.of("c2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Positions.of("d2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Positions.of("e2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Positions.of("f2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Positions.of("g2"), new Piece(PieceType.PAWN, Team.WHITE));
        pieces.put(Positions.of("h2"), new Piece(PieceType.PAWN, Team.WHITE));

        return Collections.unmodifiableMap(pieces);
    }
}
