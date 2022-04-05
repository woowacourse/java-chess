package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;

public final class Finish implements State {

    @Override
    public State proceed(final ChessBoard chessBoard, final GameCommand gameCommand) {
        throw new IllegalStateException("종료 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
