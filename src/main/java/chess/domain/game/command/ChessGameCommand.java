package chess.domain.game.command;

import chess.domain.game.ChessGame;

public interface ChessGameCommand {

    void execute(ChessGame chessGame);
}
