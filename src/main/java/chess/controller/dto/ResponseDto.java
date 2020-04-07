package chess.controller.dto;

import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.domain.result.Status;

import java.util.Map;
import java.util.Objects;

public class ResponseDto {

    private Map<Position, String> board;
    private Status status;

    public ResponseDto(Map<Position, String> board) {
        this.board = board;
    }

    public ResponseDto(Status status) {
        this.status = status;
    }

    public Map<Position, String> getBoard() {
        return board;
    }

    public Map<Player, Double> getStatus() {
        if (Objects.nonNull(status)) {
            return status.getStatus();
        }
        return null;
    }

    public Player getWinner() {
        return status.getWinner();
    }
}
