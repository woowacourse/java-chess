package chess.controller.dto;

import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Map;

public class ResponseDto {

    private Map<Position, String> board;
    private Map<Team, Double> scores;

    public ResponseDto(Map<Position, String> board, Map<Team, Double> scores) {
        this.board = board;
        this.scores = scores;
    }

    public ResponseDto(Map<Position, String> board) {
        this.board = board;
    }

    public static ResponseDto of(Map<Position, String> board) {
        return new ResponseDto(board);
    }

    public Map<Position, String> getBoard() {
        return board;
    }

    public Map<Team, Double> getScores() {
        return scores;
    }
}
