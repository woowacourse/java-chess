package chess.domain;

import chess.domain.RuleImpl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoardGenerator {
    private BoardGenerator() {
    }

    public static Map<Position, Square> generate() {
        Map<Position, Square> map = new TreeMap<>();
        List<Position> positions = getColumns(8);
        map.put(positions.get(0), Square.of(positions.get(0), Piece.of(Piece.Color.BLACK, Rook.getInstance())));
        map.put(positions.get(1), Square.of(positions.get(1), Piece.of(Piece.Color.BLACK, Knight.getInstance())));
        map.put(positions.get(2), Square.of(positions.get(2), Piece.of(Piece.Color.BLACK, Bishop.getInstance())));
        map.put(positions.get(3), Square.of(positions.get(3), Piece.of(Piece.Color.BLACK, Queen.getInstance())));
        map.put(positions.get(4), Square.of(positions.get(4), Piece.of(Piece.Color.BLACK, King.getInstance())));
        map.put(positions.get(5), Square.of(positions.get(5), Piece.of(Piece.Color.BLACK, Bishop.getInstance())));
        map.put(positions.get(6), Square.of(positions.get(6), Piece.of(Piece.Color.BLACK, Knight.getInstance())));
        map.put(positions.get(7), Square.of(positions.get(7), Piece.of(Piece.Color.BLACK, Rook.getInstance())));

        //분리 case2
        positions = getColumns(7);
        for (Position position : positions) {
            map.put(position, Square.of(position, Piece.of(Piece.Color.BLACK, Pawn.FIRST_TOP)));
        }

        for (int i = 6; i >= 3; i--) {
            positions = getColumns(i);
            for (Position position : positions) {
                map.put(position, Square.of(position, Piece.of(Piece.Color.EMPTY, Empty.getInstance())));
            }
        }


        //분리 case2
        positions = getColumns(2);
        for (Position position : positions) {
            map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
        }

        positions = getColumns(1);
        map.put(positions.get(0), Square.of(positions.get(0), Piece.of(Piece.Color.WHITE, Rook.getInstance())));
        map.put(positions.get(1), Square.of(positions.get(1), Piece.of(Piece.Color.WHITE, Knight.getInstance())));
        map.put(positions.get(2), Square.of(positions.get(2), Piece.of(Piece.Color.WHITE, Bishop.getInstance())));
        map.put(positions.get(3), Square.of(positions.get(3), Piece.of(Piece.Color.WHITE, Queen.getInstance())));
        map.put(positions.get(4), Square.of(positions.get(4), Piece.of(Piece.Color.WHITE, King.getInstance())));
        map.put(positions.get(5), Square.of(positions.get(5), Piece.of(Piece.Color.WHITE, Bishop.getInstance())));
        map.put(positions.get(6), Square.of(positions.get(6), Piece.of(Piece.Color.WHITE, Knight.getInstance())));
        map.put(positions.get(7), Square.of(positions.get(7), Piece.of(Piece.Color.WHITE, Rook.getInstance())));

        return map;
    }

    private static List<Position> getColumns(final int rowIndex) {
        List<Position> positions = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            Position position = Position.of(String.valueOf(rowIndex), String.valueOf((char) i));
            positions.add(position);
        }
        return positions;
    }
}
