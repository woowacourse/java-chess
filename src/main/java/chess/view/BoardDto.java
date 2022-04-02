package chess.view;

import chess.domain.pieces.Piece;
import chess.domain.pieces.Symbol;
import chess.domain.position.Column;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {

    private final List<List<String>> boardSymbols;

    private BoardDto(List<List<String>> boardSymbols) {
        this.boardSymbols = boardSymbols;
    }

    public static BoardDto of(Map<String, Piece> pieces) {
        List<List<String>> boardSymbols = new ArrayList<>();
        for (Integer row : Row.valuesByDescending()) {
            boardSymbols.add(makeLine(pieces, row));
        }
        return new BoardDto(boardSymbols);
    }

    private static List<String> makeLine(Map<String, Piece> pieces, Integer row) {
        List<String> symbols = new ArrayList<>();
        for (Column column : Column.values()) {
            symbols.add(findByKey(pieces, row, column));
        }
        return symbols;
    }

    private static String findByKey(Map<String, Piece> pieces, Integer row, Column column) {
        if (pieces.containsKey(row + column.name())) {
            return pieces.get(row + column.name()).symbol();
        }
        return Symbol.BLANK.value();
    }

    public List<List<String>> get() {
        return boardSymbols;
    }
}
