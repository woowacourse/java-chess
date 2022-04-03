package chess.controller;

import chess.domain.game.state.ChessGame;
import chess.domain.piece.Color;
import java.util.function.BiConsumer;

public class StatusCommand implements Command {

    private final BiConsumer<Double, Double> consumer;

    public StatusCommand(BiConsumer<Double, Double> consumer) {
        this.consumer = consumer;
    }

    @Override
    public ChessGame run(ChessGame chessGame, String fromPosition, String toPosition) {
        consumer.accept(chessGame.calculateScore(Color.WHITE), chessGame.calculateScore(Color.BLACK));
        return chessGame;
    }
}
