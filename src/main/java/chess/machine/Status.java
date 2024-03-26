package chess.machine;

import chess.domain.chessGame.BoardScore;
import chess.domain.chessGame.ChessGame;
import chess.domain.piece.Color;
import chess.view.OutputView;

public class Status implements Command {

    private static final String INPUT_COMMAND = "status";

    private Status(String input) {
        if (!input.equals(INPUT_COMMAND)) {
            throw new IllegalArgumentException("상태 명령이 아닙니다");
        }
    }

    public static Status of(String input) {
        return new Status(input);
    }

    @Override
    public void conductCommand(ChessGame chessGame, OutputView outputView) {
        Result result = new Result(chessGame);

        outputView.printScore(chessGame.calculateScore(Color.BLACK), Color.BLACK);
        outputView.printScore(chessGame.calculateScore(Color.BLACK), Color.WHITE);
        outputView.printWinner(result.winner());
    }

    private static class Result {

        private final ChessGame chessGame;

        Result(ChessGame chessGame) {
            this.chessGame = chessGame;
        }

        Color winner() {
            if (chessGame.hasSameDeadKingColor(Color.EMPTY)) {
                return winnerByScore();
            }
            if (chessGame.hasSameDeadKingColor(Color.WHITE)) {
                return Color.BLACK;
            }
            return Color.WHITE;
        }

        Color winnerByScore() {
            BoardScore blackScore = chessGame.calculateScore(Color.BLACK);
            BoardScore whiteScore = chessGame.calculateScore(Color.WHITE);

            if (blackScore.asDouble() > whiteScore.asDouble()) {
                return Color.BLACK;
            }
            if (blackScore.asDouble() < whiteScore.asDouble()) {
                return Color.WHITE;
            }
            return Color.EMPTY;
        }
    }
}
