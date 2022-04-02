package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardStatusCalculator;
import chess.domain.piece.Piece;

public class StatusScore {

    private final double white;
    private final double black;
    private final String gameResultMessage;

    private StatusScore(final double statusOfWhite,
                        final double statusOfBlack,
                        final String gameResultMessage) {
        this.white = statusOfWhite;
        this.black = statusOfBlack;
        this.gameResultMessage = gameResultMessage;
    }

    private static StatusScore of(final double whiteStatus,
                                  final double blackStatus,
                                  final String gameResultMessage) {
        return new StatusScore(whiteStatus, blackStatus, gameResultMessage);
    }

    public static StatusScore from(final Board board) {
        final BoardStatusCalculator statusCalculator = new BoardStatusCalculator(board);
        final double whiteStatus = statusCalculator.calculate(piece -> !piece.isBlack());
        final double blackStatus = statusCalculator.calculate(Piece::isBlack);

        final GameResult gameResult = GameResult.findWinner(board, whiteStatus, blackStatus);

        return StatusScore.of(whiteStatus, blackStatus, gameResult.getMessage());
    }

    public double getWhite() {
        return white;
    }

    public double getBlack() {
        return black;
    }

    public String getGameResultMessage() {
        return gameResultMessage;
    }
}
