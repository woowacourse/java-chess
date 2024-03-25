package model.menu;

import java.util.List;
import model.ChessGame;

public class Start implements Menu {

    @Override
    public Menu play(List<String> command, ChessGame chessGame) {
        return null;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void play2(ChessGame chessGame) {
        chessGame.start();
    }
}
