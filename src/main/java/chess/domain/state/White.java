package chess.domain.state;

public class White implements State {

    @Override
    public State start() {
        throw new IllegalArgumentException("White 상태에서는 start할 수 없습니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public State changeTurn() {
        return new Black();
    }
}
