package chess.controller.dto;

import chess.domain.game.Turn;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.domain.status.Status;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResponseDto {

    private List<Long> roomId;
    private Map<Position, String> board;
    private Status status;
    private String message;
    private Turn turn;
    private Player winner;
    private long id;

    public ResponseDto(final List<Long> roomId) {
        this.roomId = roomId;
    }

    public ResponseDto(Map<Position, String> board, Turn turn, Status status, long id) {
        this.board = board;
        this.turn = turn;
        this.status = status;
        this.id = id;
    }

    public ResponseDto(final String message) {
        this.message = message;
    }

    public Map<Position, String> getBoard() {
        return board;
    }

    public void setBoard(final Map<Position, String> board) {
        this.board = board;
    }

    public Map<Player, Double> getStatus() {
        if (Objects.nonNull(status)) {
            return status.getStatus();
        }
        return null;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(final Player winner) {
        this.winner = winner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Turn getTurn() {
        return turn;
    }

    public long getId() {
        return id;
    }

    public List<Long> getRoomId() {
        return roomId;
    }

    public void setRoomId(final List<Long> roomId) {
        this.roomId = roomId;
    }
}