package chess.domain.strategy.initialize;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class KingInitializer implements InitializeStrategy {

    private static final String KING_SYMBOL = "k";

    private enum InitialKing {
        BLACK_KING(Position.of("e8"), new King(PieceType.KING, Team.BLACK)),
        WHITE_KING(Position.of("e1"), new King(PieceType.KING, Team.WHITE));

        private final Position position;
        private final Piece piece;

        InitialKing(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }

        public static Map<Position, Piece> initializeKings() {
            Map<Position, Piece> kings = Arrays.stream(values())
                    .collect(Collectors.toMap(entry -> entry.position, entry -> entry.piece,
                            (e1, e2) -> e1, HashMap::new));
            return Collections.unmodifiableMap(kings);
        }
    }

    @Override
    public Map<Position, Piece> initialize() {
        return InitialKing.initializeKings();
    }

    @Override
    public Map<Position, Piece> initialize(Map<String, String> pieceOnBoards) {
        Map<String, String> kings = pieceOnBoards.entrySet().stream()
                .filter(entry -> entry.getValue().substring(0, 1).toLowerCase().equals(KING_SYMBOL))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

        Map<Position, Piece> board = kings.entrySet().stream()
                .collect(Collectors.toMap(entry -> Position.convert(entry.getKey()),
                        entry -> initializeKing(entry.getValue()),
                        (e1, e2) -> e1, HashMap::new));

        return Collections.unmodifiableMap(board);
    }

    private Piece initializeKing(String key) {
        String pieceTypeSymbol = key.substring(0, 1);
        String teamName = key.substring(1).toUpperCase();
        PieceType pieceType = PieceType.of(pieceTypeSymbol);
        Team team = Team.valueOf(teamName);
        return new King(pieceType, team);
    }
}
