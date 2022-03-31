package chess.model.state.finished;

import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.State;
import java.util.List;
import java.util.Map;

public abstract class Finished implements State {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State proceed(List<String> command) {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되어 더 이상 진행 할 수 없습니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되어 데이터를 가져올 수 없습니다.");
    }
}
