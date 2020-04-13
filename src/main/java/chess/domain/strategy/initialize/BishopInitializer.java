package chess.domain.strategy.initialize;

import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class BishopInitializer implements InitializeStrategy {
    private static final String BISHOP_SYMBOL = "b";

    private enum InitialBishop {
        BLACK_LEFT(Position.of("c8"), new Bishop(PieceType.BISHOP, Team.BLACK)),
        BLACK_RIGHT(Position.of("f8"), new Bishop(PieceType.BISHOP, Team.BLACK)),
        WHITE_LEFT(Position.of("c1"), new Bishop(PieceType.BISHOP, Team.WHITE)),
        WHITE_RIGHT(Position.of("f1"), new Bishop(PieceType.BISHOP, Team.WHITE));

        private final Position position;
        private final Piece piece;

        InitialBishop(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }

        public static Map<Position, Piece> initializeBishops() {
            Map<Position, Piece> bishops = Arrays.stream(values())
                    .collect(Collectors.toMap(entry -> entry.position, entry -> entry.piece,
                            (e1, e2) -> e1, HashMap::new));
            return Collections.unmodifiableMap(bishops);
        }
    }

    @Override
    public Map<Position, Piece> initialize() {
        return InitialBishop.initializeBishops();
    }

    @Override
    public Map<Position, Piece> initialize(Map<String, String> pieceOnBoards) {
        Map<String, String> bishops = pieceOnBoards.entrySet().stream()
                .filter(entry -> entry.getValue().substring(0, 1).toLowerCase().equals(BISHOP_SYMBOL))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

        Map<Position, Piece> board = bishops.entrySet().stream()
                .collect(Collectors.toMap(entry -> Position.convert(entry.getKey()),
                        entry -> initializeBishop(entry.getValue()),
                        (e1, e2) -> e1, HashMap::new));

        return Collections.unmodifiableMap(board);
    }

    private Piece initializeBishop(String key) {
        String pieceTypeSymbol = key.substring(0, 1);
        String teamName = key.substring(1).toUpperCase();
        PieceType pieceType = PieceType.of(pieceTypeSymbol);
        Team team = Team.valueOf(teamName);
        return new Bishop(pieceType, team);
    }
}