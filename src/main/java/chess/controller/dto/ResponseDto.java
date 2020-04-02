package chess.controller.dto;

import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Map;

public class ResponseDto {

    private Map<Position, PieceDto> board;
    private Map<Team, Double> scores;
    private String message;

    public ResponseDto(Map<Position, PieceDto> board, Map<Team, Double> scores) {
        this.board = board;
        this.scores = scores;
    }

    public ResponseDto(final Map<Position, PieceDto> board, final Map<Team, Double> scores, final String message) {
        this.board = board;
        this.scores = scores;
        this.message = message;
    }

    public Map<Position, PieceDto> getBoard() {
        return board;
    }

    public String getMessage() {
        return message;
    }

    public Map<Team, Double> getScores() {
        return scores;
    }
}
