package chess.controller.command;

import chess.domain.ChessGame;

public interface Command {

    void execute(ChessGame chessGame);
}
