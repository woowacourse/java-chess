package chess.domain.game.command;

import chess.domain.game.ChessGame;

public class StartCommand implements Command {

    @Override
    public void execute(ChessGame chessGame) {
        chessGame.start();
    }
}
