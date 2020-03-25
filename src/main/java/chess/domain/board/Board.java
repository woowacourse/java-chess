package chess.domain.board;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private Map<Position, PieceType> board;

    public Board() {
        this.board = BoardInitializer.initializeAll();
    }

    public Map<String, String> get() {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(),
                        entry -> entry.getValue().getName(),
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
