package chess.controller;

import chess.domain.game.state.ChessGame;
import java.util.List;
import java.util.function.BiFunction;

public class ChessAction {

    private final BiFunction<List<String>, ChessGame, ChessGame> action;

    public ChessAction(BiFunction<List<String>, ChessGame, ChessGame> action) {
        this.action = action;
    }

    public ChessGame execute(List<String> commands, ChessGame chessGame) {
        return action.apply(commands, chessGame);
    }
}
