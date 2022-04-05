package chess.web;

import chess.domain.ChessGame;
import chess.domain.piece.Color;
import chess.dto.BoardDto;
import chess.dto.StatusDto;

import java.util.Map;
import java.util.Objects;

public class Response {

    private static final String EMPTY = "";
    private static final String EXCEPTION_SUFFIX = " ❗";
    private static final String STATUS_EMOJI = "\uD83C\uDFC6";
    private static final String STATUS_FORMAT = STATUS_EMOJI + " 검은색 : %.1f점, 흰색 : %.1f점 " + STATUS_EMOJI;

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

    public String getStatus() {
        if (chessGame.isEnded()) {
            Map<Color, Double> statuses = StatusDto.from(chessGame.getBoard()).getStatus();
            return String.format(STATUS_FORMAT,  statuses.get(Color.BLACK),  statuses.get(Color.WHITE));
        }
        return EMPTY;
    }

    public String getExceptionMessage() {
        if (!Objects.equals(exceptionMessage, EMPTY)) {
            return exceptionMessage + EXCEPTION_SUFFIX;
        }
        return exceptionMessage;
    }
}
