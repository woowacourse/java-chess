package service.command;

import service.ChessGame;

import java.util.function.Consumer;

public interface ChessCommand {

    void execute(ChessGame chessGame, Consumer<ChessGame> callBack);
}
