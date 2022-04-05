package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    private final Map<String, String> board;
    private final String turn;

    private BoardDto(Map<String, String> board, String turn) {
        this.board = board;
        this.turn = turn;
    }

    public static BoardDto of(Map<Position, Piece> board, String turn) {
        Map<String, String> aBoard = new HashMap<>();

        for (Position position : board.keySet()) {
            aBoard.put(position.convertToString(), board.get(position).convertToString());
        }
        return new BoardDto(aBoard, turn);
    }
}
