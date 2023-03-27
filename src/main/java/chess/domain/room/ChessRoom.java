package chess.domain.room;

import chess.controller.ChessState;

public class ChessRoom {

    private final ChessState state;

    private ChessRoom(final ChessState state) {
        this.state = state;
    }

    public static ChessRoom of(final String state) {
        ChessState chessState = ChessState.bind(state);
        return new ChessRoom(chessState);
    }

    public ChessState getState() {
        return state;
    }
}
