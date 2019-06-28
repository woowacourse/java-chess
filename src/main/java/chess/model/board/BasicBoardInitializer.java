package chess.model.board;

import chess.model.Column;
import chess.model.Row;
import chess.model.Square;
import chess.model.unit.*;

import java.util.HashMap;
import java.util.Map;

public class BasicBoardInitializer implements BoardInitializer {
    @Override
    public Map<Square, Piece> initialize() {
        Map<Square, Piece> board = new HashMap<>();
        setFirstLine(board, Side.WHITE, Column.Col_1);
        setSecondLine(board, Side.WHITE, Column.Col_2);
        setSecondLine(board, Side.BLACK, Column.Col_7);
        setFirstLine(board, Side.BLACK, Column.Col_8);
        return board;
    }

    private void setFirstLine(Map<Square, Piece> map, Side side, Column column) {
        map.put(Square.of(column, Row.Row_A), new Rook(side));
        map.put(Square.of(column, Row.Row_B), new Knight(side));
        map.put(Square.of(column, Row.Row_C), new Bishop(side));
        map.put(Square.of(column, Row.Row_D), new Queen(side));
        map.put(Square.of(column, Row.Row_E), new King(side));
        map.put(Square.of(column, Row.Row_F), new Bishop(side));
        map.put(Square.of(column, Row.Row_G), new Knight(side));
        map.put(Square.of(column, Row.Row_H), new Rook(side));
    }

    private void setSecondLine(Map<Square, Piece> map, Side side, Column column) {
        for (Row row : Row.values()) {
            map.put(Square.of(column, row), new Pawn(side));
        }
    }
}