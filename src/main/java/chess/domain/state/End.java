package chess.domain.state;

public class End implements State {
    @Override
    public State start() {
       throw new IllegalArgumentException("End 상태에서는 start할 수 없습니다.");
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }

    @Override
    public State changeTurn() {
        throw new IllegalArgumentException("End 상태에서는 chageTurn할 수 없습니다.");
    }
}
