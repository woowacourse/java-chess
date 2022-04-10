package chess.dto;

import chess.domain.game.ChessGame;
import chess.domain.game.Status;
import chess.domain.state.Running;
import chess.domain.state.State;

public class ResponseStatusDto {

    private final String code;
    private final String message;
    private final String state;
    private final double whiteScore;
    private final double blackScore;
    private final String result;

    public ResponseStatusDto(String error, String message) {
        this(error, message, "", 0, 0, null);
    }

    public ResponseStatusDto(String code, String message, String state, double whiteScore, double blackScore, String result) {
        this.code = code;
        this.message = message;
        this.state = state;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.result = result;
    }

    public static ResponseStatusDto createResponseStatusDto(Status status, ChessGame chessGame) {
        return new ResponseStatusDto(
                "success", "", convertToString(chessGame.getState()),
                status.getWhiteScore().getScore(),
                status.getBlackScore().getScore(),
                status.getWinnerColor().getName());
    }

    public static ResponseStatusDto createErrorResponseDto(String message) {
        return new ResponseStatusDto("error", message);
    }

    private static String convertToString(State state) {
        if (state instanceof Running) {
            return "running";
        }
        return "finished";
    }
}
