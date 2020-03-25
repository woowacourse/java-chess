package chess.domain.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BishopInitializer implements InitializeStrategy {
    @Override
    public Map<Position, Piece> initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of("c8"), new Piece(PieceType.BISHOP, Team.BLACK));
        pieces.put(Position.of("c1"), new Piece(PieceType.BISHOP, Team.WHITE));
        pieces.put(Position.of("f8"), new Piece(PieceType.BISHOP, Team.BLACK));
        pieces.put(Position.of("f1"), new Piece(PieceType.BISHOP, Team.WHITE));

        return Collections.unmodifiableMap(pieces);
    }
}