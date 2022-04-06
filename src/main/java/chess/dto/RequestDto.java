package chess.dto;

import chess.Command;
import chess.domain.position.Position;
import spark.Request;

public class RequestDto {
    private final Command command;

    private Position from;
    private Position to;

    public RequestDto(Command command, Request request) {
        this.command = command;
        if (command == Command.MOVE) {
            this.from = Position.of(request.queryParams("from"));
            this.to = Position.of(request.queryParams("to"));
        }
    }
    public Position getFrom() {
        return from;
    }
    public Position getTo() {
        return to;
    }
}