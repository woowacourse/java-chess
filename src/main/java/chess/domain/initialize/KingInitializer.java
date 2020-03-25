package chess.domain.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class KingInitializer implements InitializeStrategy {
    @Override
    public Map<Position, Piece> initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of("e8"), new Piece(PieceType.KING, Team.BLACK));
        pieces.put(Position.of("e1"), new Piece(PieceType.KING, Team.WHITE));

        return Collections.unmodifiableMap(pieces);
    }
}
