package chess;

public class Play implements State {

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move() {
        return new Finish();
    }
}
