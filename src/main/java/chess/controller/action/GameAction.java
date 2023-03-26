package chess.controller.action;

import chess.controller.Command;
import chess.domain.chessgame.ChessGame;

@FunctionalInterface
public interface GameAction {
    ChessGame execute(final Command command, ChessGame chessGame);
}
