package chess.dto;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.GamePiece;

public class DtoConverter {

    public static List<LineDto> convertFrom(Map<Position, GamePiece> gamePieces) {
        Map<Row, LineDto> rows = gamePieces.entrySet()
                .stream()
                .collect(groupingBy(entry -> entry.getKey().getRow(),    // key(row)
                        () -> new TreeMap<>(Collections.reverseOrder()),    // 리턴타입은 reversed TreeMap
                        mapping(entry -> new GamePieceDto(entry.getValue().getName(), entry.getValue().getPlayerColor(),
                                        entry.getKey()),
                                collectingAndThen(toList(), LineDto::new)))); // value(Line)
        return new ArrayList<>(rows.values());
    }
}
