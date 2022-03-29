package chess.command;

import chess.domain.state.ChessState;

public class End implements Command {

    @Override
    public ChessState execute(ChessState chessState) {
        return chessState.end();
    }
}
