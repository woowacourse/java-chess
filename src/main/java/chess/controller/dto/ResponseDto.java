package chess.controller.dto;

import chess.domain.position.Position;

import java.util.Map;

public class ResponseDto {

    private Map<Position, String> board;

    public ResponseDto(Map<Position, String> board) {
        this.board = board;
    }

    public static ResponseDto of(Map<Position, String> board) {
        return new ResponseDto(board);
    }

    public Map<Position, String> getBoard() {
        return board;
    }
}
