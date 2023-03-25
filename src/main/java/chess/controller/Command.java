package chess.controller;

import chess.domain.ChessGame;

import java.util.List;

public abstract class Command {

    protected final ChessGame chessGame;
    protected final CommandType commandType;

    protected Command(ChessGame chessGame, CommandType commandType) {
        this.chessGame = chessGame;
        this.commandType = commandType;
    }

    public abstract Command execute(List<String> input);

    public boolean isSameType(CommandType commandType) {
        return this.commandType == commandType;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
