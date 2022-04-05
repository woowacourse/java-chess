package chess.web;

import chess.domain.ChessGame;
import chess.dto.BoardDto;

import java.util.Map;
import java.util.Objects;

public class Response {

    private static final String EMPTY = "";
    private static final String EXCEPTION_SUFFIX = " ❗";

    private final ChessGame chessGame;
    private String exceptionMessage;

    private Response(final ChessGame chessGame, final String exceptionMessage) {
        this.chessGame = chessGame;
        this.exceptionMessage = exceptionMessage;
    }

    public static Response init(final ChessGame chessGame) {
        return new Response(chessGame, EMPTY);
    }

    public void success() {
        this.exceptionMessage = EMPTY;
    }

    public void exception(final String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Map<String, String> getBoard() {
        return BoardDto.from(chessGame.getBoard()).getBoard();
    }

    public String getExceptionMessage() {
        if (!Objects.equals(exceptionMessage, EMPTY)) {
            return exceptionMessage + EXCEPTION_SUFFIX;
        }
        return exceptionMessage;
    }
}
