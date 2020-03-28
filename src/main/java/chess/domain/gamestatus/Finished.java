package chess.domain.gamestatus;

public class Finished implements GameStatus {

    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임이 끝난 뒤 게임을 한번 더 할 수 없습니다.");
    }

    @Override
    public GameStatus move(String fromPosition, String toPosition) {
        throw new UnsupportedOperationException("게임이 이미 끝났으므로 기물을 움직일 수 없습니다.");
    }

    @Override
    public String getBoardString() {
        return null;
    }
}
