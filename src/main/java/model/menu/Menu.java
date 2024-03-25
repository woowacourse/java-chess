package model.menu;

import java.util.List;
import model.ChessGame;

public interface Menu {

    Menu play(final List<String> command, final ChessGame chessGame);

    boolean isRunning();

    void play2(ChessGame chessGame);
}
