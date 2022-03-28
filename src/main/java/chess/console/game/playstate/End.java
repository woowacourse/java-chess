package chess.console.game.playstate;

public class End implements PlayState {

    @Override
    public PlayState run(String command) {
        throw new UnsupportedOperationException("게임이 종료된 상태에서는 게임을 실행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
