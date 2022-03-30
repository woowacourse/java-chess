package chess.controller;

import chess.domain.Command;
import chess.domain.board.state.BoardInitializer;
import chess.domain.board.state.BoardState;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private static final int COMMAND_INDEX = 0;
    private static final int START_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public void run() {
        OutputView.printStartView();

        if (requestFirstCommand() != Command.START) {
            return;
        }

        BoardState boardState = BoardInitializer.initBoard();
        playRound(boardState);

        OutputView.printResult(boardState);
    }

    private Command requestFirstCommand() {
        try {
            return InputView.requestFirstCommand();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return requestFirstCommand();
        }
    }

    private void playRound(BoardState boardState) {
        while (!boardState.isEnd()) {
            OutputView.printBoard(boardState);
            executeTurn(boardState);
        }
    }

    private void executeTurn(BoardState boardState) {
        try {
            executeCommand(boardState);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printError(e.getMessage());
            OutputView.printBoard(boardState);
            executeTurn(boardState);
        }
    }

    private void executeCommand(BoardState boardState) {
        List<String> input = InputView.requestCommandLine();

        if (Command.inGameCommand(input.get(COMMAND_INDEX)) == Command.END) {
            boardState.terminate();
            return;
        }

        if (Command.inGameCommand(input.get(COMMAND_INDEX)) == Command.MOVE) {
            boardState.move(new Position(input.get(START_POSITION_INDEX)),
                    new Position(input.get(TARGET_POSITION_INDEX)));
        }
    }
}
