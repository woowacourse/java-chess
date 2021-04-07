package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.position.Position;
import chess.dto.response.ResponseCode;
import chess.exception.ChessException;

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
        throw new ChessException(ResponseCode.GAME_NOT_START);
    }

    @Override
    public GameState status() {
        throw new ChessException(ResponseCode.GAME_NOT_START);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
