package chess.machine;

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
        outputView.printScore(chessBoard.calculateScore(Color.BLACK), Color.BLACK);
        outputView.printScore(chessBoard.calculateScore(Color.WHITE), Color.WHITE);
    }
}
