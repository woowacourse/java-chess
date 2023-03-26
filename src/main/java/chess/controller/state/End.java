package chess.controller.state;

import chess.controller.Command;
import chess.domain.Score;
import chess.domain.chess.ChessGame;

public final class End implements State {
    @Override
    public State checkCommand(final Command command) {
        throw new IllegalArgumentException("게임이 끝났습니다.");
    }

    @Override
    public boolean isRun() {
        return false;
    }

    public State run(ChessGame chessGame) {
        Score score = new Score();
        System.out.println("status");
        return new End();
    }
}
