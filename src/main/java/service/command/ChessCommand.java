package service.command;

import service.ChessGame;

import java.util.function.BiConsumer;

public interface ChessCommand {

    void execute(ChessGame chessGame, BiConsumer<ChessGame, Boolean> callBack);
}
