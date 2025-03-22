package chess;

import chess.piece.Piece;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import java.util.HashMap;
import java.util.Map;

public class Initializer {

    public static Map<Position, Piece> initializeBoard() {
        Map<Position, Piece> board = new HashMap<>();
        for (final Row row : Row.values()) {
            for (final Column column : Column.values()) {
                board.put(new Position(row, column), null); // TODO : ??
            }
        }
        return board;
    }
}
