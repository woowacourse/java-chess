package chess.controller;

public class TerminateGame implements GameStatus {

    @Override
    public boolean isPlayable() {
        return false;
    }

    @Override
    public GameStatus play() {
        return this;
    }
}
