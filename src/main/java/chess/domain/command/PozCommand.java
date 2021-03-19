package chess.domain.command;

import chess.domain.ChessGame;

public abstract class PozCommand implements Command {
    private final ChessGame chessGame;

    public PozCommand(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
