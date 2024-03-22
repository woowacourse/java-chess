package model.menu;

import java.util.List;
import model.ChessGame;

public interface ChessStatus {

    ChessStatus play(final List<String> command, final ChessGame chessGame);

    boolean isRunning();
}
