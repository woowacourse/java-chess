package chess.controller.command;

import chess.domain.game.ChessGame;

public interface Command {

    void execute(ChessGame chessGame);
}
