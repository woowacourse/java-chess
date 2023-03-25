package chess.controller;

import chess.domain.ChessGame;

public abstract class Command {

    private final ChessGame chessGame;
    private final GameCommand gameCommand;

    protected Command(ChessGame chessGame, GameCommand gameCommand) {
        this.chessGame = chessGame;
        this.gameCommand = gameCommand;
    }

    public abstract void execute();

    public boolean isSameType(GameCommand gameCommand) {
        return this.gameCommand == gameCommand;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
