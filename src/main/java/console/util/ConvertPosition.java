package console.util;

import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import java.util.HashMap;
import java.util.Map;

public class ConvertPosition {
    private Map<Character, Row> convertRow = new HashMap<>();
    private Map<Character, Column> convertColumn = new HashMap<>();

    {
        convertRow.put('1',Row.ONE);
        convertRow.put('2',Row.TWO);
        convertRow.put('3',Row.THREE);
        convertRow.put('4',Row.FOUR);
        convertRow.put('5',Row.FIVE);
        convertRow.put('6',Row.SIX);
        convertRow.put('7',Row.SEVEN);
        convertRow.put('8',Row.EIGHT);
    }

    {
        convertColumn.put('a', Column.A);
        convertColumn.put('b', Column.B);
        convertColumn.put('c', Column.C);
        convertColumn.put('d', Column.D);
        convertColumn.put('e', Column.E);
        convertColumn.put('f', Column.F);
        convertColumn.put('g', Column.G);
        convertColumn.put('h', Column.H);
    }

    public Position convert(String input){
        return new Position(convertRow.get(input.charAt(1)), convertColumn.get(input.charAt(0)));
    }

}
