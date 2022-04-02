package chess.domain.board;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Row;
import chess.domain.position.Square;

public class InitialBoardGenerator implements BoardGenerator {

    @Override
    public Map<Square, Piece> generate() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        for (Row row : Row.values()) {
            createRow(board, row);
        }
        return board;
    }

    private static void createRow(Map<Square, Piece> board, Row row) {
        for (Column column : Column.values()) {
            board.put(new Square(column, row), Piece.from(column, row));
        }
    }
}
