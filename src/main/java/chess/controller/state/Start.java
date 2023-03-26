package chess.controller.state;

import chess.controller.Command;
import chess.domain.chess.ChessGame;
import chess.domain.piece.TeamColor;

public final class Start implements State {

    private final ChessGame chessGame;

    public Start(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State checkCommand(final Command command) {
        if (command.isStart()) {
            return new Move(chessGame, TeamColor.WHITE);
        }
        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
