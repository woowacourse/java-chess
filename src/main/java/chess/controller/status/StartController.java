package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;

public final class StartController implements Status {

    private final ChessGame chessGame;

    public StartController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public Status checkCommand(final Command command, final Runnable runnable) {
        if (command.isEnd()) {
            return new EndController();
        }
        if (command.isMove()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        runnable.run();
        return new MoveController(chessGame, CampType.WHITE);
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
