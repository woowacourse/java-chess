package domain.commend;

import domain.pieces.Pieces;

public class State {
    private GameState gameState;

    private State(Pieces pieces) {
        gameState = new Waiting(pieces);
    }

    public static State of(Pieces pieces) {
        return new State(pieces);
    }

    public void start() {
        gameState = gameState.start();
    }

    public void end() {
        gameState = gameState.end();
    }

    public void move() {
        gameState = gameState.move();
    }

    public boolean isFinished() {
        return gameState.isFinished();
    }

    public Pieces getPieces() {
        return gameState.pieces();
    }
}
