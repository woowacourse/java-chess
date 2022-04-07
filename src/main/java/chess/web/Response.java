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

    private final int chessGameId;
    private final ChessGame chessGame;
    private final String exceptionMessage;

    private Response(final int chessGameId, final ChessGame chessGame, final String exceptionMessage) {
        this.chessGameId = chessGameId;
        this.chessGame = chessGame;
        this.exceptionMessage = exceptionMessage;
    }

    public static Response init(final int chessGameId, final ChessGame chessGame) {
        return new Response(chessGameId, chessGame, EMPTY);
    }

    public static Response exception(final int chessGameId, final ChessGame chessGame, final String exceptionMessage) {
        return new Response(chessGameId, chessGame, exceptionMessage);
    }

    public int getChessGameId() {
        return chessGameId;
    }

    public Map<String, String> getBoard() {
        return BoardDto.from(chessGame.getBoard()).getBoard();
    }

    public String getStatus() {
        Map<Color, Double> statuses = StatusDto.from(chessGame.getBoard()).getStatus();
        return String.format(STATUS_FORMAT,  statuses.get(Color.BLACK),  statuses.get(Color.WHITE));
    }

    public String getExceptionMessage() {
        if (!Objects.equals(exceptionMessage, EMPTY)) {
            return exceptionMessage + EXCEPTION_SUFFIX;
        }
        return exceptionMessage;
    }
}
