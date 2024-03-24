package domain.game.command;

import domain.game.ChessGame;

import java.util.function.Consumer;

public interface ChessCommand {

    void execute(ChessGame chessGame, Consumer<ChessGame> callBack);
}
