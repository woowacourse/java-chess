package chess;

public class Ready implements State {

    @Override
    public State start() {
        return new Play();
    }

    @Override
    public State move() {
        throw new UnsupportedOperationException();
    }
}
