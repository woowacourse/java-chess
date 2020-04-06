package chess.domain.strategy.initialize;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class QueenInitializer implements InitializeStrategy {
    private enum InitialQueen {
        BLACK_QUEEN(Position.of("d8"), new Queen(PieceType.QUEEN, Team.BLACK)),
        WHITE_QUEEN(Position.of("d1"), new Queen(PieceType.QUEEN, Team.WHITE));

        private final Position position;
        private final Piece piece;

        InitialQueen(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }

        public static Map<Position, Piece> initializeQueens() {
            Map<Position, Piece> queens = Arrays.stream(values())
                    .collect(Collectors.toMap(entry -> entry.position, entry -> entry.piece,
                            (e1, e2) -> e1, HashMap::new));
            return Collections.unmodifiableMap(queens);
        }
    }

    @Override
    public Map<Position, Piece> initialize() {
        return InitialQueen.initializeQueens();
    }

    @Override
    public Map<Position, Piece> webInitialize(Map<String, String> pieceOnBoards) {
        Map<String, String> queens = pieceOnBoards.entrySet().stream()
                .filter(entry -> entry.getValue().substring(0, 1).toLowerCase().equals("q"))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

        Map<Position, Piece> board = queens.entrySet().stream()
                .collect(Collectors.toMap(entry -> Position.convert(entry.getKey()),
                        entry -> initializeQueen(entry.getValue()),
                        (e1, e2) -> e1, HashMap::new));

        return Collections.unmodifiableMap(board);
    }

    private Piece initializeQueen(String key) {
        String pieceTypeSymbol = key.substring(0, 1);
        String teamName = key.substring(2).toUpperCase();
        PieceType pieceType = PieceType.of(pieceTypeSymbol);
        Team team = Team.valueOf(teamName);
        return new Queen(pieceType, team);
    }
}