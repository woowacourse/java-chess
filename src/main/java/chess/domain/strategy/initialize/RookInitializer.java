package chess.domain.strategy.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class RookInitializer implements InitializeStrategy {
    private enum InitialRook {
        BLACK_LEFT(Position.of("a8"), new Rook(PieceType.ROOK, Team.BLACK)),
        BLACK_RIGHT(Position.of("h8"), new Rook(PieceType.ROOK, Team.BLACK)),
        WHITE_LEFT(Position.of("a1"), new Rook(PieceType.ROOK, Team.WHITE)),
        WHITE_RIGHT(Position.of("h1"), new Rook(PieceType.ROOK, Team.WHITE));

        private final Position position;
        private final Piece piece;

        InitialRook(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }

        public static Map<Position, Piece> initializeRooks() {
            Map<Position, Piece> rooks = Arrays.stream(values())
                    .collect(Collectors.toMap(entry -> entry.position, entry -> entry.piece,
                            (e1, e2) -> e1, HashMap::new));
            return Collections.unmodifiableMap(rooks);
        }
    }

    @Override
    public Map<Position, Piece> initialize() {
        return InitialRook.initializeRooks();
    }
}