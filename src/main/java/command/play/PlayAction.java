package command.play;

import domain.ChessGame;

public interface PlayAction {
    boolean execute(ChessGame chessGame);
}
