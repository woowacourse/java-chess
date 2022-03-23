package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public class Ready implements State {

    @Override
    public State start() {
        return new White(new Board());
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalArgumentException("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Override
    public State move() {
        throw new IllegalArgumentException("[ERROR] 게임이 시작되지 않았습니다.");
    }
}
