package chess.controller.dto;

import chess.domain.board.Board;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {
    private final Map<String, String> board;

    public BoardDto(final Board board) {
        this.board = toDto(board);
    }

    public Map<String, String> toDto(Board board) {
        Map<String, String> parseResult = board.get()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toSymbol(),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        return Collections.unmodifiableMap(parseResult);
    }

    public Map<String, String> get() {
        return Collections.unmodifiableMap(this.board);
    }
}
