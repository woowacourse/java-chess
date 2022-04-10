package chess.domain.piece.generator;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;

public interface PiecesGenerator {

    Map<Position, Piece> generate();

    static void fillEmptyPiece(final Map<Position, Piece> pieces) {
        for (final Column column : Column.values()) {
            fillEmptyPieceInColumn(column, pieces);
        }
    }

    static void fillEmptyPieceInColumn(final Column column, final Map<Position, Piece> pieces) {
        for (final Row row : Row.values()) {
            pieces.computeIfAbsent(Position.of(column, row), value -> EmptyPiece.getInstance());
        }
    }
}
