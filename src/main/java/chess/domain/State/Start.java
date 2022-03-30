package chess.domain.State;

import chess.domain.ChessBoard;
import chess.domain.CommandType;
import chess.domain.GameCommand;
import chess.domain.piece.Color;

public abstract class Start implements State {

    @Override
    public final State proceed(ChessBoard chessBoard, GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            return start();
        }
        if (gameCommand.isSameCommandType(CommandType.END)) {
            return end();
        }
        if (gameCommand.isSameCommandType(CommandType.MOVE)) {
            return move(chessBoard, gameCommand);
        }
        return this;
    }

    private State start() {
        throw new IllegalStateException("시작 상태에서는 다시 시작할 수 없습니다.");
    }

    private State end() {
        return new Finish();
    }

    public abstract State move(ChessBoard chessBoard, GameCommand gameCommand);

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public abstract Color getWinner();
}
