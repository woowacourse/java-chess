package chess.controller;

import chess.domain.Board;
import chess.domain.game.state.ChessGame;
import java.util.function.Consumer;

public class StartCommand implements Command {

    private final Consumer<Board> consumer;

    public StartCommand(Consumer<Board> consumer) {
        this.consumer = consumer;
    }

    @Override
    public ChessGame run(ChessGame chessGame, String fromPosition, String toPosition) {
        ChessGame newGame = chessGame.initBoard();
        consumer.accept(newGame.getBoard());
        return newGame;
    }
}
