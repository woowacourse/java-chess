package chess.machine;

import chess.domain.chessBoard.BoardScore;
import chess.domain.chessBoard.ChessBoard;
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
    public void conductCommand(ChessBoard chessBoard, OutputView outputView) {
        Result result = new Result(chessBoard);

        outputView.printScore(result.blackScore, Color.BLACK);
        outputView.printScore(result.whiteScore, Color.WHITE);
        outputView.printWinner(result.winner());
    }

    private static class Result {

        final BoardScore blackScore;
        final BoardScore whiteScore;

        Result(ChessBoard chessBoard) {
            blackScore = chessBoard.calculateScore(Color.BLACK);
            whiteScore = chessBoard.calculateScore(Color.WHITE);
        }

        Color winner() {
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
