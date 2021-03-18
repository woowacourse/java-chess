package chess.domain.dto;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardDto {
    private final Map<Position, Square> board;

    private BoardDto(Map<Position, Square> board) {
        this.board = Collections.unmodifiableMap(new HashMap<>(board));
    }

    public static BoardDto from(Board board) {
        return new BoardDto(board.board());
    }

    public Square findByPosition(Position position) {
        return this.board.get(position);
    }
}
