package chess.domain.gamestatus;

import java.util.List;

public class NothingHappened implements GameStatus {
    @Override
    public GameStatus move(String fromPosition, String toPosition) {
        throw new UnsupportedOperationException("현 상태에서 수행할 수 없는 동작입니다.");
    }

    @Override
    public List<List<String>> getBoard() {
        throw new UnsupportedOperationException("현 상태에서 수행할 수 없는 동작입니다.");
    }
}
