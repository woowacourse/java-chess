package chess.domain;

import chess.domain.rule.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoardGenerator {
    private static final int ROW_8 = 8;
    private static final int ROW_7 = 7;
    private static final int ROW_6 = 6;
    private static final int ROW_3 = 3;
    private static final int ROW_2 = 2;
    private static final int ROW_1 = 1;

    private BoardGenerator() {
    }

    public static Map<Position, Piece> generate() {
        Map<Position, Piece> map = new TreeMap<>();

        initBlackPieces(map, ROW_8);
        initBlackPawns(map, ROW_7);
        initEmptyPieces(map, ROW_6, ROW_3);
        initWhitePawns(map, ROW_2);
        initWhitePieces(map, ROW_1);

        return map;
    }

    private static void initBlackPieces(final Map<Position, Piece> map, final int rowIndex) {
        List<Position> positions = getColumns(rowIndex);
        map.put(positions.get(0), Piece.of(positions.get(0), Piece.Color.BLACK, Rook.getInstance()));
        map.put(positions.get(1), Piece.of(positions.get(1), Piece.Color.BLACK, Knight.getInstance()));
        map.put(positions.get(2), Piece.of(positions.get(2), Piece.Color.BLACK, Bishop.getInstance()));
        map.put(positions.get(3), Piece.of(positions.get(3), Piece.Color.BLACK, Queen.getInstance()));
        map.put(positions.get(4), Piece.of(positions.get(4), Piece.Color.BLACK, King.getInstance()));
        map.put(positions.get(5), Piece.of(positions.get(5), Piece.Color.BLACK, Bishop.getInstance()));
        map.put(positions.get(6), Piece.of(positions.get(6), Piece.Color.BLACK, Knight.getInstance()));
        map.put(positions.get(7), Piece.of(positions.get(7), Piece.Color.BLACK, Rook.getInstance()));

    }

    private static void initBlackPawns(final Map<Position, Piece> map, final int rowIndex) {
        List<Position> positions = getColumns(rowIndex);
        for (Position position : positions) {
            map.put(position, Piece.of(position, Piece.Color.BLACK, Pawn.FIRST_TOP));
        }
    }

    private static void initEmptyPieces(final Map<Position, Piece> map, final int starIndex, final int endIndex) {
        for (int i = starIndex; i >= endIndex; i--) {
            final List<Position> positions = getColumns(i);
            for (Position position : positions) {
                map.put(position, Piece.of(position, Piece.Color.EMPTY, Empty.getInstance()));
            }
        }
    }

    private static void initWhitePawns(final Map<Position, Piece> map, final int rowIndex) {
        List<Position> positions = getColumns(rowIndex);
        for (Position position : positions) {
            map.put(position, Piece.of(position, Piece.Color.WHITE, Pawn.FIRST_BOTTOM));
        }
    }

    private static void initWhitePieces(final Map<Position, Piece> map, final int rowIndex) {
        List<Position> positions = getColumns(rowIndex);
        map.put(positions.get(0), Piece.of(positions.get(0), Piece.Color.WHITE, Rook.getInstance()));
        map.put(positions.get(1), Piece.of(positions.get(1), Piece.Color.WHITE, Knight.getInstance()));
        map.put(positions.get(2), Piece.of(positions.get(2), Piece.Color.WHITE, Bishop.getInstance()));
        map.put(positions.get(3), Piece.of(positions.get(3), Piece.Color.WHITE, Queen.getInstance()));
        map.put(positions.get(4), Piece.of(positions.get(4), Piece.Color.WHITE, King.getInstance()));
        map.put(positions.get(5), Piece.of(positions.get(5), Piece.Color.WHITE, Bishop.getInstance()));
        map.put(positions.get(6), Piece.of(positions.get(6), Piece.Color.WHITE, Knight.getInstance()));
        map.put(positions.get(7), Piece.of(positions.get(7), Piece.Color.WHITE, Rook.getInstance()));
    }

    private static List<Position> getColumns(final int rowIndex) {
        List<Position> positions = new ArrayList<>();
        for (int i = Column.MIN; i <= Column.MAX; i++) {
            Position position = Position.of(String.valueOf(rowIndex), String.valueOf((char) i));
            positions.add(position);
        }
        return positions;
    }
}
