package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {

    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int START_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public void run() {
        OutputView.printStartView();

        if (requestFirstCommand() != Command.START) {
            return;
        }

        Board board = new Board();
        playRound(board);

        OutputView.printResult(board);
    }

    private Command requestFirstCommand() {
        try {
            return Command.firstCommand(InputView.requestCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return requestFirstCommand();
        }
    }

    private void playRound(Board board) {
        while (!board.isEnd()) {
            OutputView.printBoard(board);
            executeTurn(board);
        }
    }

    private void executeTurn(Board board) {
        try {
            executeCommand(board);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printError(e.getMessage());
            OutputView.printBoard(board);
            executeTurn(board);
        }
    }

    private void executeCommand(Board board) {
        List<String> input = List.of(InputView.requestCommand().split(DELIMITER));

        if (Command.inGameCommand(input.get(COMMAND_INDEX)) == Command.END) {
            board.terminate();
            return;
        }

        if (Command.inGameCommand(input.get(COMMAND_INDEX)) == Command.MOVE) {
            board.move(new Position(input.get(START_POSITION_INDEX)), new Position(input.get(TARGET_POSITION_INDEX)));
        }
    }
}
