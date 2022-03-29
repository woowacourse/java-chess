package chess.command;

import chess.domain.state.ChessState;
import chess.view.OutputView;

public class Status implements Command {

    @Override
    public ChessState execute(ChessState chessState) {
        OutputView.printStatus(chessState.createStatus());
        return chessState;
    }
}
