package chess.domain.state;

import chess.domain.board.Board;

public abstract class Playing extends GameStarted {

    static final String IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE = "본인의 기물이 아닙니다.";

    public Playing(Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Winner findWinner() {
        throw new IllegalStateException("게임이 아직 진행 중 입니다.");
    }

    @Override
    public GameState terminate() {
        return new Terminate(board);
    }
}
