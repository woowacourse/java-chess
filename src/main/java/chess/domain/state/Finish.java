package chess.domain.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.command.CommandType;
import chess.domain.command.GameCommand;

public final class Finish implements State {

    @Override
    public State proceed(final ChessBoard chessBoard, final GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            return start();
        }
        throw new IllegalStateException("종료 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    private State start() {
        return new WhiteRunning();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String toString() {
        return "Finish";
    }
}
