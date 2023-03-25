package chess.domain.game.command;

import chess.domain.game.ChessGame;

public interface Query {

    Object execute(ChessGame chessGame);
}
