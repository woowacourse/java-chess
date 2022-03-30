package chess;

import chess.domain.Board;
import chess.domain.Command;
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
        printCurrentTurnPlayer();
        state = runCommand();
        if (state.isStarted()) {
            printCurrentTurnResult();
        }
        if (!state.isEnded()) {
            run();
        }
    }

    private void printCurrentTurnPlayer() {
        if (state.isStarted()){
            OutputView.printTurnMessage(state.getColor().getName());
        }
    }

    private State runCommand() {
        try {
            final String[] commands = InputView.requestCommands();
            final Command command = Command.from(commands[MAIN_COMMAND_INDEX]);
            return command.run(state, commands);
        } catch (IllegalArgumentException | IllegalStateException | UnsupportedOperationException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return runCommand();
        }
    }

    private void printCurrentTurnResult() {
        Board board = state.getBoard();
        Color thisTurn = state.getColor();
        final Score myScore = new Score(board, thisTurn);
        final Score opponentScore = new Score(board,thisTurn.getOpposite());

        OutputView.printScore(thisTurn.getName(), myScore.getValue());
        OutputView.printScore(thisTurn.getOpposite().getName(), opponentScore.getValue());
        OutputView.printResult(thisTurn.getName(), Result.decide(myScore, opponentScore).getName());
        OutputView.printBoard(board.getBoard());
    }

}
