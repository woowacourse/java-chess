package chess.controller.state;

import chess.controller.Command;
import chess.domain.game.ChessGame;

public final class Start implements State {

    private final ChessGame chessGame;

    public Start(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State checkCommand(final Command command) {
        if (command.isStart()) {
            return new Move(chessGame, chessGame.getCurrentTeamColor());
        }
        if (command.isEnd()) {
            return new End();
        }
        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
