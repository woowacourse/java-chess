package chess.controller;

import chess.domain.Command;
import chess.domain.piece.Position;
import chess.domain.state.GameState;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private static final int COMMAND_INDEX = 0;
    private static final int START_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private GameState boardState;

    public ChessGameController(GameState boardState) {
        this.boardState = boardState;
    }

    public void run() {
        OutputView.printStartView();

        if (requestFirstCommand() != Command.START) {
            return;
        }

        playRound();

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

    private void playRound() {
        while (!boardState.isEnd()) {
            OutputView.printBoard(boardState);
            executeTurn();
        }
    }

    private void executeTurn() {
        try {
            executeCommand();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printError(e.getMessage());
            OutputView.printBoard(boardState);
            executeTurn();
        }
    }

    private void executeCommand() {
        List<String> input = InputView.requestCommandLine();

        if (Command.inGameCommand(input.get(COMMAND_INDEX)) == Command.END) {
            boardState = boardState.terminate();
            return;
        }

        if (Command.inGameCommand(input.get(COMMAND_INDEX)) == Command.MOVE) {
            boardState = boardState.move(new Position(input.get(START_POSITION_INDEX)),
                    new Position(input.get(TARGET_POSITION_INDEX)));
            System.out.println(boardState.isBlackTurn());
        }
    }
}
