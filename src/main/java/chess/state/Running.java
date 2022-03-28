package chess.state;

import chess.Board;
import chess.Player;

import java.util.Map;

public abstract class Running extends Started {

    public Running(Board board) {
        super(board);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public Map<Player, Double> calculateScore() {
        throw new RuntimeException("[ERROR] 게임이 끝나지 않아 점수를 계산 할 수 없습니다.");
    }
}
