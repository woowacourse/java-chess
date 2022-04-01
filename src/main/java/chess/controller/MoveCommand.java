package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.game.state.ChessGame;
import java.util.function.Consumer;

public class MoveCommand implements Command {

    private final Consumer<Board> consumer;

    public MoveCommand(Consumer<Board> consumer) {
        this.consumer = consumer;
    }

    @Override
    public ChessGame run(ChessGame chessGame, String fromPosition, String toPosition) {
        ChessGame movedGame = chessGame.movePiece(Position.valueOf(fromPosition),
                Position.valueOf(toPosition));
        consumer.accept(movedGame.getBoard());
        return movedGame;
    }
}
