package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardStatusCalculator;
import chess.domain.piece.Piece;

public class StatusScore {
    private static final int RESULT_CRITERIA = 0;

    private final double white;
    private final double black;
    private final GameResult gameResult;

    private StatusScore(final double statusOfWhite,
                        final double statusOfBlack,
                        final GameResult gameResult) {
        this.white = statusOfWhite;
        this.black = statusOfBlack;
        this.gameResult = gameResult;
    }

    public static StatusScore from(final Board board) {
        final BoardStatusCalculator statusCalculator = new BoardStatusCalculator(board);
        final double whiteStatus = statusCalculator.calculate(piece -> !piece.isBlack());
        final double blackStatus = statusCalculator.calculate(Piece::isBlack);
        final GameResult gameResult = findWinner(board, whiteStatus, blackStatus);
        return new StatusScore(whiteStatus, blackStatus, gameResult);
    }

    private static GameResult findWinner(final Board board,
                                         final double statusOfWhite,
                                         final double statusOfBlack) {
        if (board.hasBlackKingCaptured()) {
            return GameResult.BLACK_LOSE;
        }
        if (board.hasWhiteKingCaptured()) {
            return GameResult.BLACK_WIN;
        }
        return getResultWhenNoKingCaptured(statusOfWhite, statusOfBlack);
    }

    private static GameResult getResultWhenNoKingCaptured(final double statusOfWhite, final double statusOfBlack) {
        final int resultNumber = Double.compare(statusOfWhite, statusOfBlack);
        if (resultNumber < RESULT_CRITERIA) {
            return GameResult.BLACK_WIN;
        }
        if (resultNumber > RESULT_CRITERIA) {
            return GameResult.BLACK_LOSE;
        }
        return GameResult.DRAW;
    }

    public double getWhite() {
        return white;
    }

    public double getBlack() {
        return black;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
