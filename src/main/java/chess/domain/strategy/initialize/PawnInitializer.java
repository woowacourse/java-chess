package chess.domain.strategy.initialize;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class PawnInitializer implements InitializeStrategy {
    private enum InitialPawn {
        BLACK_A7(Position.of("a7"), new Pawn(PieceType.PAWN, Team.BLACK)),
        BLACK_B7(Position.of("b7"), new Pawn(PieceType.PAWN, Team.BLACK)),
        BLACK_C7(Position.of("c7"), new Pawn(PieceType.PAWN, Team.BLACK)),
        BLACK_D7(Position.of("d7"), new Pawn(PieceType.PAWN, Team.BLACK)),
        BLACK_E7(Position.of("e7"), new Pawn(PieceType.PAWN, Team.BLACK)),
        BLACK_F7(Position.of("f7"), new Pawn(PieceType.PAWN, Team.BLACK)),
        BLACK_G7(Position.of("g7"), new Pawn(PieceType.PAWN, Team.BLACK)),
        BLACK_H7(Position.of("h7"), new Pawn(PieceType.PAWN, Team.BLACK)),

        WHITE_A2(Position.of("a2"), new Pawn(PieceType.PAWN, Team.WHITE)),
        WHITE_B2(Position.of("b2"), new Pawn(PieceType.PAWN, Team.WHITE)),
        WHITE_C2(Position.of("c2"), new Pawn(PieceType.PAWN, Team.WHITE)),
        WHITE_D2(Position.of("d2"), new Pawn(PieceType.PAWN, Team.WHITE)),
        WHITE_E2(Position.of("e2"), new Pawn(PieceType.PAWN, Team.WHITE)),
        WHITE_F2(Position.of("f2"), new Pawn(PieceType.PAWN, Team.WHITE)),
        WHITE_G2(Position.of("g2"), new Pawn(PieceType.PAWN, Team.WHITE)),
        WHITE_H2(Position.of("h2"), new Pawn(PieceType.PAWN, Team.WHITE));

        private final Position position;
        private final Piece piece;

        InitialPawn(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }

        public static Map<Position, Piece> initialPawns() {
            Map<Position, Piece> pawns = Arrays.stream(values())
                    .collect(Collectors.toMap(entry -> entry.position, entry -> entry.piece,
                            (e1, e2) -> e1, HashMap::new));
            return Collections.unmodifiableMap(pawns);
        }
    }

    @Override
    public Map<Position, Piece> initialize() {
        return InitialPawn.initialPawns();
    }

    @Override
    public Map<Position, Piece> webInitialize(Map<String, String> pieceOnBoards) {
        Map<String, String> pawns = pieceOnBoards.entrySet().stream()
                .filter(entry -> entry.getValue().substring(0, 1).toLowerCase().equals("p"))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

        Map<Position, Piece> board = pawns.entrySet().stream()
                .collect(Collectors.toMap(entry -> Position.convert(entry.getKey()),
                        entry -> initializePawn(entry.getValue()),
                        (e1, e2) -> e1, HashMap::new));

        return Collections.unmodifiableMap(board);
    }

    private Piece initializePawn(String key) {
        String pieceTypeSymbol = key.substring(0, 1);
        String teamName = key.substring(2).toUpperCase();
        PieceType pieceType = PieceType.of(pieceTypeSymbol);
        Team team = Team.valueOf(teamName);
        return new Pawn(pieceType, team);
    }
}
