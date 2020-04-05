package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import chess.dto.GamePieceDto;
import chess.dto.LineDto;

public class Line {

    private final List<GamePiece> line;

    private Line(List<GamePiece> line) {
        this.line = line;
    }

    public static List<LineDto> listByRow(Map<Position, GamePiece> gamePieces) {
        Map<Row, LineDto> rows = gamePieces.entrySet()
                .stream()
                .collect(groupingBy(entry -> entry.getKey().getRow(),    // key(row)
                        () -> new TreeMap<>(Collections.reverseOrder()),    // 리턴타입은 reversed TreeMap
                        mapping(entry -> new GamePieceDto(entry.getValue().getName(), entry.getValue().getPlayerColor(), entry.getKey()),
                                collectingAndThen(toList(), LineDto::new)))); // value(Line)
        return new ArrayList<>(rows.values());
    }

    public static List<Line> listByColumn(Map<Position, GamePiece> gamePieces) {
        Map<Column, Line> columns = gamePieces.entrySet()
                .stream()
                .collect(groupingBy(entry -> entry.getKey().getColumn(),       // key(column)
                        mapping(Map.Entry::getValue, collectingAndThen(toList(), Line::new)))); // value(Line)

        return new ArrayList<>(columns.values());
    }

    public int countPawnOf(PlayerColor playerColor) {
        return (int)line.stream()
                .filter(piece -> piece.is(playerColor))
                .filter(GamePiece::isPawn)
                .count();
    }
}
