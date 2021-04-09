package chess.domain.game.state;

import chess.domain.board.Board;

public class StateConverter {

    private final Board board;

    public StateConverter(final Board board) {
        this.board = board;
    }

    public State matchedState(final String state) {
        if (state == "Init") {
            return new Init(board);
        }
        if (state == "BlackTurn") {
            return new BlackTurn(board);
        }
        if (state == "WhiteTurn") {
            return new WhiteTurn(board);
        }
        if (state == "BlackWin") {
            return new BlackWin(board);
        }
        if (state == "WhiteWin") {
            return new WhiteWin(board);
        }
        return new End(board);
    }
}
