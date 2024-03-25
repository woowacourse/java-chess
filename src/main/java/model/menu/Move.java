package model.menu;

import model.ChessGame;
import model.position.Moving;
import model.position.Position;

public class Move implements Menu {

    private final Moving moving;

    public Move(Position currentPosition, Position nextPosition) {
        this.moving = new Moving(currentPosition, nextPosition);
    }

    @Override
    public void play(ChessGame chessGame) {
        chessGame.move(moving);
    }
}
