package chess.domain.game.command;

import chess.domain.game.ChessGame;

public interface Command {

    void execute(ChessGame chessGame);
}
