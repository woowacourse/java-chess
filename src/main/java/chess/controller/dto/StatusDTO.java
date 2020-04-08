package chess.controller.dto;

import chess.domain.board.Status;

public class StatusDTO {

    private final String message;

    private StatusDTO(String message) {
        this.message = message;
    }

    public static StatusDTO create(Status status) {
        return new StatusDTO(status.getMessage());
    }

    public String getMessage() {
        return message;
    }
}
