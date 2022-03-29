package chess;

import chess.domain.state.ChessState;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class Chess {

    private static final String COMMAND_DISTRIBUTOR = " ";
    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final int COMMAND = 0;
    private static final int MOVE_COMMAND_LENGTH = 3;

    public Chess() {

    }

    public void run() {
        OutputView.printStartMessage();
        ChessState chessState = new Ready();
        while (!chessState.isEnd()) {
            chessState = proceed(chessState);
        }
        OutputView.printStatus(chessState.createStatus());
    }

    private ChessState proceed(final ChessState chessState) {
        try {
            return proceedOnce(chessState);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return proceed(chessState);
        }
    }

    private ChessState proceedOnce(final ChessState chessState) {
        final String[] args = InputView.input()
                .split(COMMAND_DISTRIBUTOR, -1);
        if (args.length != 1 && args.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
        final Command command = Command.from(args[COMMAND]);
        return operate(chessState, command, args);
    }

    private ChessState operate(ChessState state, Command command, String... commandArgs) {
        if (command == Command.STATUS) {
            OutputView.printStatus(state.createStatus());
        }
        state = state.execute(command, commandArgs);
        if (!state.isEnd()) {
            OutputView.printBoard(state.getBoard().getPieces());
        }
        return state;
    }
}
