package chess.domain.state;

import chess.domain.grid.ChessGame;
import chess.domain.position.Position;

public final class Status extends Finished {

    @Override
    public GameState start() {
        throw new IllegalArgumentException("게임 종료");
    }

    @Override
    public GameState status() {
        throw new IllegalArgumentException("게임 종료");
    }

    @Override
    public GameState end() {
        throw new IllegalArgumentException("게임 종료");
    }

    @Override
    public GameState move(ChessGame game, Position sourcePosition, Position targetPosition) {
        throw new IllegalArgumentException("게임 종료");
    }
}
