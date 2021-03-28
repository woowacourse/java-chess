package chess.domain.command;

import chess.domain.ChessGame;

public interface Command {
    boolean isMatch(String commandText);

    void execute(ChessGame chessGame, String[] splitCommand);

    boolean isMustShowBoard();

    boolean isMustShowStatus();
}
