package chess.command;

import chess.domain.state.ChessState;
import chess.view.OutputView;

public class Start implements Command {

    @Override
    public ChessState execute(ChessState chessState) {
        ChessState runningState = chessState.start();
        OutputView.printBoard(runningState.getPieces());
        return runningState;
    }
}
