package chess.domain.State;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.piece.Color;

public final class Finish implements State {

    @Override
    public State proceed(final ChessBoard chessBoard, final GameCommand gameCommand) {
        throw new IllegalStateException("종료 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Color getWinner() {
        throw new IllegalStateException("사용할 수 없습니다.");
    }
}
