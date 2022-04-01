package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    public static Map<Position, AbstractPiece> createChessPieces() {
        Map<Position, AbstractPiece> pieces = new HashMap<>();

        putPiecesExceptPawnOnRow(pieces, Row.RANK_1, Color.WHITE);
        putPawnOnRank(pieces, Row.RANK_2, Color.WHITE);
        putPawnOnRank(pieces, Row.RANK_7, Color.BLACK);
        putPiecesExceptPawnOnRow(pieces, Row.RANK_8, Color.BLACK);

        return pieces;
    }

    private static void putPiecesExceptPawnOnRow(Map<Position, AbstractPiece> pieces, Row row, Color color) {
        pieces.put(Position.of(Column.A, row), new Rook(color));
        pieces.put(Position.of(Column.B, row), new Knight(color));
        pieces.put(Position.of(Column.C, row), new Bishop(color));
        pieces.put(Position.of(Column.D, row), new Queen(color));
        pieces.put(Position.of(Column.E, row), new King(color));
        pieces.put(Position.of(Column.F, row), new Bishop(color));
        pieces.put(Position.of(Column.G, row), new Knight(color));
        pieces.put(Position.of(Column.H, row), new Rook(color));
    }

    private static void putPawnOnRank(Map<Position, AbstractPiece> pieces, Row row, Color color) {
        for (Column column : Column.values()) {
            Position position = Position.of(column, row);
            pieces.put(position, new Pawn(color));
        }
    }
}
