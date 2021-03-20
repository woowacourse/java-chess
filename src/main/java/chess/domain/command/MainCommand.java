package chess.domain.command;

import chess.domain.ChessGame;

public abstract class MainCommand implements Command {
    private final ChessGame chessGame;

    public MainCommand(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
