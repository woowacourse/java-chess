package chess.domain.game.state;

import chess.domain.board.Board;

public class StateConverter {

    private final Board board;

    public StateConverter(final Board board) {
        this.board = board;
    }

    public State matchedState(final String state) {
        if (state.equalsIgnoreCase("게임 시작 전")) {
            return new Init(board);
        }
        if (state.equalsIgnoreCase("흑색 차례")) {
            return new BlackTurn(board);
        }
        if (state.equalsIgnoreCase("백색 차례")) {
            return new WhiteTurn(board);
        }
        if (state.equalsIgnoreCase("흑색 승리")) {
            return new BlackWin(board);
        }
        if (state.equalsIgnoreCase("백색 승리")) {
            return new WhiteWin(board);
        }
        return new End(board);
    }
}
