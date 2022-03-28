package chess.state;

import chess.Board;

public class End extends Finished {

    protected End(Board board) {
        super(board);
    }

    @Override
    public State proceed(String command) {
        throw new IllegalArgumentException("[ERROR] 올바른 명령을 입력해주세요.");
    }
}
