package chess.domain;

import chess.domain.piece.*;
import chess.domain.piece.character.Team;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private BoardFactory() {
    }

    public static Map<Position, Piece> generateStartBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createPawnRow(2, Team.WHITE));
        pieces.putAll(createPawnRow(7, Team.BLACK));
        pieces.putAll(createEdgeRow(1, Team.WHITE));
        pieces.putAll(createEdgeRow(8, Team.BLACK));
        return pieces;
    }

    private static Map<Position, Piece> createPawnRow(int row, Team team) {
        Map<Position, Piece> pawnRow = new HashMap<>();
        for (int column = 1; column <= 8; column++) {
            pawnRow.put(Position.of(row, column), new Pawn(team));
        }
        return pawnRow;
    }

    private static Map<Position, Piece> createEdgeRow(int row, Team team) {
        return new HashMap<>(Map.of(
                Position.of(row, 1), new Rook(team),
                Position.of(row, 2), new Knight(team),
                Position.of(row, 3), new Bishop(team),
                Position.of(row, 4), new Queen(team),
                Position.of(row, 5), new King(team),
                Position.of(row, 6), new Bishop(team),
                Position.of(row, 7), new Knight(team),
                Position.of(row, 8), new Rook(team)
        ));
    }
}
