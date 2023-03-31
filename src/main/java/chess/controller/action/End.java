package chess.controller.action;

import chess.controller.Command;
import chess.domain.chessgame.ChessGame;

public class End implements GameAction {
    @Override
    public ChessGame execute(final Command command, final ChessGame chessGame) {
        return chessGame.end();
    }
}
