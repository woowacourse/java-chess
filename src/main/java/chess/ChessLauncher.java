package chess;

import chess.command.Command;
import chess.command.CommandFactory;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.state.ChessState;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessLauncher {

    public void run() {
        OutputView.printStartMessage();
        ChessState chessState = new Ready(new Board(new CreateCompleteBoardStrategy()));
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
        Command command = CommandFactory.find(InputView.input());
        return command.execute(chessState);
    }
}
