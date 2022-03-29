package chess.console.state;

public class End implements State {

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State run(String[] inputs) {
        throw new IllegalStateException("게임이 종료되어 실행이 불가능합니다.");
    }
}
