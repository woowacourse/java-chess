package chess;

import chess.domain.Board;
import chess.domain.Result;
import chess.domain.Score;
import chess.domain.piece.Color;
import chess.domain.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    private static final int MAIN_COMMAND_INDEX = 0;

    private State state;

    public ChessGame(final State state) {
        this.state = state;
    }

    public void run() {
        while (!state.isEnded()) {
            printCurrentTurnPlayer();
            final String[] commands = InputView.requestCommands();
            final Command command = findCommand(commands);
            runCommand(command, commands);
            printStatus(command);
        }
    }

    private void printCurrentTurnPlayer() {
        if (state.isStarted()) {
            OutputView.printBoard(state.getBoard().getBoard());
            OutputView.printTurnMessage(state.getColor().getName());
        }
    }

    private Command findCommand(final String[] commands) {
        try {
            return Command.from(commands[MAIN_COMMAND_INDEX]);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return findCommand(InputView.requestCommands());
        }
    }

    private void runCommand(final Command command, final String[] commands) {
        try {
            state = command.run(state, commands);
        } catch (IllegalArgumentException | IllegalStateException | UnsupportedOperationException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            run();
        }
    }

    private void printStatus(final Command command) {
        if (command.isStatus()) {
            Board board = state.getBoard();
            Color thisTurn = state.getColor();
            final Score myScore = new Score(board, thisTurn);
            final Score opponentScore = new Score(board, thisTurn.getOpposite());

            OutputView.printScore(thisTurn.getName(), myScore.getValue());
            OutputView.printScore(thisTurn.getOpposite().getName(), opponentScore.getValue());
            OutputView.printResult(thisTurn.getName(), Result.decide(myScore, opponentScore).getName());
        }
    }

}
