package chess.domain.state;

import chess.domain.ChessBoard;

public abstract class Finished extends Started {

    protected Finished(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public final State start() {
        throw new UnsupportedOperationException("게임이 끝난 상태입니다.");
    }

    @Override
    public final State end() {
        throw new UnsupportedOperationException("게임이 이미 끝난 상태입니다.");
    }

    @Override
    public final State move(String source, String target) {
        throw new UnsupportedOperationException("게임이 끝나 기물을 움질일 수 없습니다.");
    }

    @Override
    public final boolean isStarted() {
        return true;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
