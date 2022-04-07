package chess.dto;

import chess.domain.game.Status;

public class ResponseStatusDto {

    private final String code;
    private final String message;
    private final double whiteScore;
    private final double blackScore;
    private final String result;

    public ResponseStatusDto(String error, String message) {
        this(error, message, 0, 0, null);
    }

    public ResponseStatusDto(String code, String message, double whiteScore, double blackScore, String result) {
        this.code = code;
        this.message = message;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.result = result;
    }

    public static ResponseStatusDto createResponseStatusDto(Status status) {
        return new ResponseStatusDto(
                "success", "",
                status.getWhiteScore().getScore(),
                status.getBlackScore().getScore(),
                status.getWinnerColor().getName());
    }

    public static ResponseStatusDto createErrorResponseDto(String message) {
        return new ResponseStatusDto("error", message);
    }
}
