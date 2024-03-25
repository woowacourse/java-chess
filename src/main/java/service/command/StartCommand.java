package service.command;

import service.ChessGame;

import java.util.function.BiConsumer;

public class StartCommand implements ChessCommand {

    @Override
    public void execute(final ChessGame chessGame, final BiConsumer<ChessGame, Boolean> callBack) {
        chessGame.start();

        callBack.accept(chessGame, false);
    }
}
