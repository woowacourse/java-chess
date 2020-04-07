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
    private Team winner;
    private String message;

    public ResponseDto(final Map<Position, PieceDto> board, final Map<Team, Double> scores, final Team turn) {
        this.board = board;
        this.scores = scores;
        this.turn = turn;
    }

    public ResponseDto(final Map<Position, PieceDto> board, final Map<Team, Double> scores, final Team turn, final Team winner, final String message) {
        this.board = board;
        this.scores = scores;
        this.turn = turn;
        this.winner = winner;
        this.message = message;
    }


    public ResponseDto() {
    }

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

    public void setRoomId(final List<Long> roomId) {
        this.roomId = roomId;
    }

    public Map<Position, PieceDto> getBoard() {
        return board;
    }

    public void setBoard(final Map<Position, PieceDto> board) {
        this.board = board;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Map<Team, Double> getScores() {
        return scores;
    }

    public void setScores(final Map<Team, Double> scores) {
        this.scores = scores;
    }

    public Team getTurn() {
        return turn;
    }

    public void setTurn(final Team turn) {
        this.turn = turn;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(final Team winner) {
        this.winner = winner;
    }
}
