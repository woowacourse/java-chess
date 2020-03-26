package chess.domain.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class KnightInitializer implements InitializeStrategy {
    @Override
    public Map<Position, Piece> initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of("b8"), new Piece(PieceType.KNIGHT, Team.BLACK));
        pieces.put(Position.of("b1"), new Piece(PieceType.KNIGHT, Team.WHITE));
        pieces.put(Position.of("g8"), new Piece(PieceType.KNIGHT, Team.BLACK));
        pieces.put(Position.of("g1"), new Piece(PieceType.KNIGHT, Team.WHITE));

        return Collections.unmodifiableMap(pieces);
    }
}