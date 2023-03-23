package chess.controller;

import chess.domain.dto.BoardDto;

public class Response {
    private final ResponseType type;
    private final String cause;
    private final BoardDto board;

    public Response(ResponseType type) {
        this.type = type;
        cause = "";
        board = null;
    }

    public Response(ResponseType type, String cause) {
        this.type = type;
        this.cause = cause;
        board = null;
    }

    public Response(ResponseType type, BoardDto board) {
        this.type = type;
        cause = "";
        this.board = board;
    }

    public ResponseType getType() {
        return type;
    }

    public String getCause() {
        return cause;
    }

    public BoardDto getBoard() {
        return board;
    }
}
