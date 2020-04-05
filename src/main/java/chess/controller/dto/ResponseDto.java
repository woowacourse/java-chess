package chess.controller.dto;

import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class ResponseDto {

    private List<Long> roomId;
    private Map<Position, PieceDto> board;
    private Map<Team, Double> scores;
    private Team turn;
    private String message;


    public ResponseDto(final List<Long> roomId, final String message) {
        this.roomId = roomId;
        this.message = message;
    }

    public ResponseDto(Map<Position, PieceDto> board, Map<Team, Double> scores) {
        this.board = board;
        this.scores = scores;
    }

    public ResponseDto(final Map<Position, PieceDto> board, final Map<Team, Double> scores, final Team turn,
                       final String message) {
        this.board = board;
        this.scores = scores;
        this.turn = turn;
        this.message = message;
    }

    public List<Long> getRoomId() {
        return roomId;
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

    public Team getTurn() {
        return turn;
    }
}
