package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private BoardFactory() {
    }

    public static Map<Position, Piece> generateStartBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createPawnRow());
        pieces.putAll(createEdgeRow(1, Team.WHITE));
        pieces.putAll(createEdgeRow(8, Team.BLACK));
        return pieces;
    }

    private static Map<Position, Piece> createPawnRow() {
        Map<Position, Piece> pawnRow = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            pawnRow.put(Position.of(7, i), new BlackPawn());
            pawnRow.put(Position.of(2, i), new WhitePawn());
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
