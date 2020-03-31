package chess.domain.strategy.initialize;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class KnightInitializer implements InitializeStrategy {
    private enum InitialKnight {
        BLACK_LEFT(Position.of("b8"), new Knight(PieceType.KNIGHT, Team.BLACK)),
        BLACK_RIGHT(Position.of("g8"), new Knight(PieceType.KNIGHT, Team.BLACK)),
        WHITE_LEFT(Position.of("b1"), new Knight(PieceType.KNIGHT, Team.WHITE)),
        WHITE_RIGHT(Position.of("g1"), new Knight(PieceType.KNIGHT, Team.WHITE));

        private final Position position;
        private final Piece piece;

        InitialKnight(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }

        public static Map<Position, Piece> initializeKnights() {
            Map<Position, Piece> knights = Arrays.stream(values())
                    .collect(Collectors.toMap(entry -> entry.position, entry -> entry.piece,
                            (e1, e2) -> e1, HashMap::new));
            return Collections.unmodifiableMap(knights);
        }
    }

    @Override
    public Map<Position, Piece> initialize() {
        return InitialKnight.initializeKnights();
    }
}