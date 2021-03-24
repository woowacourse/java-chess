package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.position.Position;

public class Finished implements GameState {
    @Override
    public GameState start() {
        throw new IllegalArgumentException("이미 게임이 끝났습니다.");
    }

    @Override
    public GameState end() {
        throw new IllegalArgumentException("이미 게임이 끝났습니다.");
    }

    @Override
    public GameState move(Grid grid, Position source, Position target) {
        throw new IllegalArgumentException("이미 게임이 끝났습니다.");
    }

    @Override
    public GameState status() {
        throw new IllegalArgumentException("이미 게임이 끝났습니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
