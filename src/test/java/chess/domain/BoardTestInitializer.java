package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;

public class BoardTestInitializer {

    public static Map<Position, Piece> init() {
        Map<Position, Piece> result = new HashMap<>();

        setBlank(result, Row.THIRD);
        setBlank(result, Row.FOURTH);
        setBlank(result, Row.FIFTH);
        setBlank(result, Row.SIXTH);
        return result;
    }

    private static void setBlank(Map<Position, Piece> result, Row row) {
        for (Column column : Column.values()) {
            result.put(new Position(column, row), new Blank());
        }
    }
}
