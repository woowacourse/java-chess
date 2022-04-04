package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public final class Ready implements State {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State execute(Command command, ChessBoard chessBoard) {
        if (command.isStart()) {
            return new White();
        }
        if (command.isEnd()) {
            return new End();
        }
        if (command.isStatus()) {
            throw new IllegalArgumentException("게임 시작 이후 점수 출력이 가능합니다.");
        }
        return new Ready();
    }

    @Override
    public String getTurn() {
        return "ready";
    }
}
