package chess.domain.status;

public class Ready implements State {

    @Override
    public State start() {
        return new White();
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public State changeTurn() {
        throw new IllegalArgumentException("Ready 상태에서는 chageTurn할 수 없습니다.");
    }
}
