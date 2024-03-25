package model.menu;

import java.util.List;
import model.ChessGame;
import model.position.Moving;
import model.position.Position;

public class Move implements Menu {

    private final Moving moving;

    public Move(Position currentPosition, Position nextPosition) {
        this.moving = new Moving(currentPosition, nextPosition);
    }

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
        chessGame.move2(moving);
    }
}
