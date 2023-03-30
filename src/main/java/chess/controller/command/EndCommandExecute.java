package chess.controller.command;

import chess.domain.chessGame.ChessGame;

public class EndCommandExecute implements CommandExecute {
    private final ChessGame chessGame;

    public EndCommandExecute(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame execute(String current, String next) {
        chessGame.end();
        return chessGame;
    }
}
