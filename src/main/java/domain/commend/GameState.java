package domain.commend;

import domain.pieces.Pieces;

public interface GameState {
    GameState start();

    GameState end();

    GameState move();

    Pieces pieces();

    boolean isFinished();
}
