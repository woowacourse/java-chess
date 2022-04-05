package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    private final Map<String, String> board;

    private BoardDto(Map<String, String> board) {
        this.board = board;
    }

    static public BoardDto of(Map<Position, Piece> board) {
        Map<String, String> aBoard = new HashMap<>();

        for (Position position : board.keySet()) {
            aBoard.put(position.convertToString(), board.get(position).convertToString());
        }
        return new BoardDto(aBoard);
    }

    public Map<String, String> getBoard() {
        return board;
    }
}
