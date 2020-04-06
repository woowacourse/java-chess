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

    @Override
    public Map<Position, Piece> webInitialize(Map<String, String> pieceOnBoards) {
        Map<String, String> knights = pieceOnBoards.entrySet().stream()
                .filter(entry -> entry.getValue().substring(0, 1).toLowerCase().equals("n"))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

        Map<Position, Piece> board = knights.entrySet().stream()
                .collect(Collectors.toMap(entry -> Position.convert(entry.getKey()),
                        entry -> initializeKnight(entry.getValue()),
                        (e1, e2) -> e1, HashMap::new));

        return Collections.unmodifiableMap(board);
    }

    private Piece initializeKnight(String key) {
        String pieceTypeSymbol = key.substring(0, 1);
        String teamName = key.substring(2).toUpperCase();
        PieceType pieceType = PieceType.of(pieceTypeSymbol);
        Team team = Team.valueOf(teamName);
        return new Knight(pieceType, team);
    }
}