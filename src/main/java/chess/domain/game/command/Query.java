package chess.domain.game.command;

import chess.domain.game.ChessGame;

public interface Query {

    <T> T execute(ChessGame chessGame);
}
