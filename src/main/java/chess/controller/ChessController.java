package chess.controller;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.running.Ready;
import chess.domain.location.Location;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void play() {
        OutputView.printGameStartMessage();
        Team currentTurn = Team.WHITE;

        Board board = BoardUtil.generateInitialBoard();
        State state = new Ready(board, "ready");
        while (!state.isFinished()) {
            state = changeStateByCommandInput(state);
            state.processCommand(currentTurn);
            if (state.isMove()) {
                currentTurn = currentTurn.reverse();
            }
            OutputView.printResult(state.getProcessResult());
        }
    }

    private State changeStateByCommandInput(State state) {
        try {
            String inputCommand = InputView.inputCommand();
            CommandType command = inputCommand(inputCommand);
            return state.changeCommand(command, inputCommand);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return changeStateByCommandInput(state);
        }
    }

    private CommandType inputCommand(String inputCommand) {
        try {
            return CommandType.from(inputCommand);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputCommand(inputCommand);
        }
    }
}
