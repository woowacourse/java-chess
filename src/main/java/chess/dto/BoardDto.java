package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardDto {
    public final Map<String, String> board;

    public BoardDto(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>();
        for (Position position : board.keySet()) {
            this.board.put(position.position(), board.get(position).name());
        }
    }

    public Map<String, String> getBoard() {
        return board;
    }
}
