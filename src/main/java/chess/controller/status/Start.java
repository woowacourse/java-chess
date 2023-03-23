package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.ChessGame;
import chess.domain.piece.TeamColor;

public final class Start implements Status {

    private final ChessGame chessGame;

    public Start(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public Status checkCommand(final Command command) {
        if (command.isEnd()) {
            return new End();
        }

        if (command.isMove()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }

        return new Move(chessGame, TeamColor.WHITE);
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
