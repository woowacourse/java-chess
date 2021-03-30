package chess.domain.state;

import chess.domain.grid.ChessGame;
import chess.domain.position.Position;

public final class Ready implements GameState {

    @Override
    public final GameState start() {
        return new WhiteTurn();
    }

    @Override
    public GameState status() {
        throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public GameState move(ChessGame game, Position source, Position target) {
        throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public final boolean isFinished() {
        return false;
    }
}
