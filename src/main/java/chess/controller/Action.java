package chess.controller;

import chess.domain.game.ChessGame;
import java.util.List;

@FunctionalInterface
public interface Action {

    void execute(final ChessGame chessGame, final List<String> command);
}
