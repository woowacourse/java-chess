package chess.controller;

import chess.domain.dto.BoardDto;
import chess.domain.dto.ResultDto;

public class Response {
    private final ResponseType type;
    private final String cause;
    private final BoardDto board;
    private final ResultDto resultDto;

    public Response(ResponseType type) {
        this.type = type;
        cause = "";
        board = null;
        resultDto = null;
    }

    public Response(ResponseType type, String cause) {
        this.type = type;
        this.cause = cause;
        board = null;
        resultDto = null;
    }

    public Response(ResponseType type, BoardDto board) {
        this.type = type;
        cause = "";
        this.board = board;
        resultDto = null;
    }

    public Response(ResponseType type, ResultDto resultDto) {
        this.type = type;
        cause = "";
        board = null;
        this.resultDto = resultDto;
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

    public ResultDto getResultDto() {
        return resultDto;
    }
}
