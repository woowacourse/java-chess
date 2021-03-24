package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.position.Position;

public final class Ready implements GameState {
    @Override
    public GameState start() {
        return new Playing();
    }

    @Override
    public GameState end() {
        return new Finished();
    }

    @Override
    public GameState move(Grid grid, Position source, Position target) {
        throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public GameState status() {
        throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
