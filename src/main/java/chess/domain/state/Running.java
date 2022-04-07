package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;

public abstract class Running extends Started {

    protected Running(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public final State start() {
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public final State end() {
        return new End(chessBoard);
    }

    @Override
    public final boolean isStarted() {
        return true;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final Result winner() {
        throw new UnsupportedOperationException("게임이 끝나지 않아 확인할 수 없습니다.");
    }
}
