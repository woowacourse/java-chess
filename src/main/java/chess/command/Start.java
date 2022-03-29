package chess.command;

import chess.domain.state.ChessState;

public class Start implements Command {

    @Override
    public ChessState execute(ChessState chessState) {
        return chessState.start();
    }
}
