package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;

public class Line {

    private final List<GamePiece> line;

    private Line(List<GamePiece> line) {
        this.line = line;
    }

    public static List<Line> listsByRow(Map<Position, GamePiece> gamePieces) {
        Map<Row, Line> rows = gamePieces.entrySet()
                .stream()
                .collect(groupingBy(entry -> entry.getKey().getRow(),       // key(row)
                        () -> new TreeMap<>(Collections.reverseOrder()),    // 리턴타입은 reversed TreeMap
                        mapping(Map.Entry::getValue, collectingAndThen(toList(), Line::new)))); // value(Line)

        return new ArrayList<>(rows.values());
    }

    public static List<Line> listsByColumn(Map<Position, GamePiece> gamePieces) {
        Map<Column, Line> columns = gamePieces.entrySet()
                .stream()
                .collect(groupingBy(entry -> entry.getKey().getColumn(),       // key(column)
                        mapping(Map.Entry::getValue, collectingAndThen(toList(), Line::new)))); // value(Line)

        return new ArrayList<>(columns.values());
    }

    public int countPawnOf(PlayerColor playerColor) {
        return (int) line.stream()
                .filter(piece -> piece.is(playerColor))
                .filter(GamePiece::isPawn)
                .count();
    }

    public List<GamePiece> getGamePieces() {
        return line;
    }
}
