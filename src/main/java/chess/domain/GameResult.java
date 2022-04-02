package chess.domain;

import chess.domain.board.Board;

public enum GameResult {
    BLACK_WIN("흑색 진영의 승리"),
    WHITE_WIN("백색 진영의 승리"),
    DRAW("무승부");

    private static final int RESULT_CRITERIA = 0;
    private final String message;

    GameResult(final String message) {
        this.message = message;
    }

    public static GameResult findWinner(final Board board,
                                        final double whiteStatus,
                                        final double blackStatus) {
        if (board.hasBlackKingCaptured()) {
            return GameResult.WHITE_WIN;
        }
        if (board.hasWhiteKingCaptured()) {
            return GameResult.BLACK_WIN;
        }
        return GameResult.of(whiteStatus, blackStatus);
    }

    public static GameResult of(final double statusOfWhite, final double statusOfBlack) {
        final int resultScore = Double.compare(statusOfWhite, statusOfBlack);
        if (resultScore < RESULT_CRITERIA) {
            return GameResult.BLACK_WIN;
        }
        if (resultScore > RESULT_CRITERIA) {
            return GameResult.WHITE_WIN;
        }
        return GameResult.DRAW;
    }

    public String getMessage() {
        return message;
    }
}
