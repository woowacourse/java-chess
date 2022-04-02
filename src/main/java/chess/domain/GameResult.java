package chess.domain;

import chess.domain.board.Board;
import java.util.Arrays;
import java.util.function.IntPredicate;

public enum GameResult {
    BLACK_WIN("흑색 진영의 승리", (resultScore) -> resultScore < 0),
    WHITE_WIN("백색 진영의 승리", (resultScore) -> resultScore > 0),
    DRAW("무승부", (resultScore) -> resultScore == 0),
    ;

    private final String message;
    private final IntPredicate winnerFinder;

    GameResult(final String message, final IntPredicate winnerFinder) {
        this.message = message;
        this.winnerFinder = winnerFinder;
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
        return Arrays.stream(GameResult.values())
            .filter(gameResult -> gameResult.winnerFinder.test(resultScore))
            .findFirst()
            .orElseThrow(IllegalStateException::new);
    }

    public String getMessage() {
        return message;
    }
}
