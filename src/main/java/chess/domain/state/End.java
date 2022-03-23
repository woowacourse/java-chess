package chess.domain.state;

import chess.domain.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public class End implements State {
    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Map<Location, Piece> getBoard() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }
}
