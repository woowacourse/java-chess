package chess.gamestate;

public class End implements GameState {

    @Override
    public GameState run(String command) {
        throw new IllegalStateException("게임이 종료된 상태에서는 게임을 실행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
