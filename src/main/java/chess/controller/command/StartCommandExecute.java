package chess.controller.command;

import chess.domain.chessGame.ChessGame;

public class StartCommandExecute implements CommandExecute {
    private ChessGame chessGame;

    public StartCommandExecute(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame execute(String ignored1, String ignored2) {
        chessGame = chessGame.start();
        return chessGame;
    }
}
