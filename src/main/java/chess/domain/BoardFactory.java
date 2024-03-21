package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.character.Team;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardFactory {
    private BoardFactory() {
    }

    public static Map<Position, Piece> generateStartBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createPawn(2, Team.WHITE));
        pieces.putAll(createPawn(7, Team.BLACK));
        pieces.putAll(createEdgeRow(1, Team.WHITE));
        pieces.putAll(createEdgeRow(8, Team.BLACK));
        return pieces;
    }

    private static Map<Position, Piece> createPawn(int row, Team team) {
        return IntStream.rangeClosed(1, 8)
                .boxed()
                .collect(Collectors.toMap(
                        column -> Position.of(row, column),
                        column -> new Pawn(team, true)
                ));
    }

    private static Map<Position, Piece> createEdgeRow(int row, Team team) {
        return new HashMap<>(Map.of(
                Position.of(row, 1), new Rook(team, true),
                Position.of(row, 2), new Knight(team, true),
                Position.of(row, 3), new Bishop(team, true),
                Position.of(row, 4), new Queen(team, true),
                Position.of(row, 5), new King(team, true),
                Position.of(row, 6), new Bishop(team, true),
                Position.of(row, 7), new Knight(team, true),
                Position.of(row, 8), new Rook(team, true)
        ));
    }
}
