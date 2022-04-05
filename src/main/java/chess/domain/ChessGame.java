package chess.domain;

import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.command.Command;
import chess.domain.command.CommandState;
import chess.domain.command.End;
import chess.domain.command.StatusResult;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.OutputView;

public class ChessGame {

    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;

    private State state;
    private CommandState commandState;

    public ChessGame(CommandState commandState) {
        this.state = Ready.start(BasicChessBoardGenerator.generator());
        this.commandState = commandState;
    }

    public void execute(String input) {
        setCommand(input);
        Command command = Command.find(input.split(DELIMITER)[COMMAND_INDEX]);
        if (command == Command.END) {
            return;
        }
        if (commandState.isStart() && command == Command.START) {
            throw new IllegalArgumentException("이미 시작했습니다.");
        }
        if (commandState.isStatus()) {
            OutputView.printStatus(getStatus());
        }
        if (commandState.isMove()) {
            state = state.movePiece(Position.of(getSource(input)), Position.of(getDestination(input)));
            checkGameEnd();
        }
    }

    private String getSource(String input) {
        return input.split(DELIMITER)[SOURCE_INDEX];
    }

    private String getDestination(String input) {
        return input.split(DELIMITER)[DESTINATION_INDEX];
    }

    private void setCommand(String input) {
        CommandState commandState = Command.getCommandState(input);
        setCommandState(commandState);
    }

    private void checkGameEnd() {
        if (state.isFinished()) {
            setCommandState(new End());
        }
    }

    private void setCommandState(CommandState commandState) {
        this.commandState = commandState;
    }

    public boolean isFinished() {
        return state.isFinished() || !commandState.isStart();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    private StatusResult getStatus() {
        return new StatusResult(state.getScore());
    }
}
