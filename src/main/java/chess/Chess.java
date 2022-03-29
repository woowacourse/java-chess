package chess;

import chess.command.CommandFactory;
import chess.domain.state.ChessState;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;

public class Chess {

    private static final String COMMAND_DISTRIBUTOR = " ";
    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_ARGUMENT_START_INDEX = 1;
    private static final int MOVE_COMMAND_SIZE = 3;

    public Chess() {

    }

    public void run() {
        OutputView.printStartMessage();
        ChessState chessState = new Ready();
        while (!chessState.isFinished()) {
            chessState = proceed(chessState);
        }
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
        final List<String> args = Arrays.asList(InputView.input()
                .split(COMMAND_DISTRIBUTOR, -1));
        if (args.size() != 1 && args.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
        final chess.command.Command command = CommandFactory.find(args.get(COMMAND_INDEX),
                args.subList(MOVE_ARGUMENT_START_INDEX, args.size()));
        return command.execute(chessState);
    }
}
