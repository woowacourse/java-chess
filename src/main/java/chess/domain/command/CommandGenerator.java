package chess.domain.command;

import chess.domain.chessgame.ChessGame;

public interface CommandGenerator {
    void execute(final String command,
                 final ChessGame chessGame,
                 final Runnable printBoardInfoToState);
}
