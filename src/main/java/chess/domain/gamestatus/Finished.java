package chess.domain.gamestatus;

import chess.domain.board.Board;

public class Finished extends Started {

    Finished() {
        super();
    }

    public Finished(Board board) {
        super(board);
    }

    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임이 끝난 뒤 게임을 한번 더 할 수 없습니다.");
    }

    @Override
    public GameStatus move(String fromPosition, String toPosition) {
        throw new UnsupportedOperationException("게임이 이미 끝났으므로 기물을 움직일 수 없습니다.");
    }

    @Override
    public GameStatus finish() {
        throw new UnsupportedOperationException("게임이 이미 끝났습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
