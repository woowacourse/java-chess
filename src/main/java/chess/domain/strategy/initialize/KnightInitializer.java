package chess.domain.strategy.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class KnightInitializer implements InitializeStrategy {
    @Override
    public Map<Position, Piece> initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Positions.of("b8"), new Piece(PieceType.KNIGHT, Team.BLACK));
        pieces.put(Positions.of("b1"), new Piece(PieceType.KNIGHT, Team.WHITE));
        pieces.put(Positions.of("g8"), new Piece(PieceType.KNIGHT, Team.BLACK));
        pieces.put(Positions.of("g1"), new Piece(PieceType.KNIGHT, Team.WHITE));

        return Collections.unmodifiableMap(pieces);
    }
}