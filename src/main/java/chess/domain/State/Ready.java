package chess.domain.State;

import chess.domain.ChessBoard;
import chess.domain.CommandType;
import chess.domain.GameCommand;
import chess.domain.piece.Color;

public final class Ready implements State {

    @Override
    public State proceed(final ChessBoard chessBoard, final GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            return start();
        }
        if (gameCommand.isSameCommandType(CommandType.END)) {
            return end();
        }
        throw new IllegalStateException("준비 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    private State start() {
        return new WhiteTurn();
    }

    private State end() {
        return new Finish();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Color getWinner() {
        throw new IllegalStateException("사용할 수 없습니다.");
    }
}
