package chess.game;

import chess.board.Board;

public class Running extends Started {

    Running(Board board) {
        super(board);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 시작되었습니다.");
    }

    @Override
    public GameState finish() {
        return new Finished(board);
    }

    @Override
    public boolean isRunnable() {
        return true;
    }
}
