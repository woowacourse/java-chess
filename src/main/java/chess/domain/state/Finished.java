package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.position.Position;
import chess.dto.response.ResponseCode;
import chess.exception.ChessException;

public class Finished implements GameState {
    @Override
    public GameState start() {
        throw new ChessException(ResponseCode.GAME_OVER);
    }

    @Override
    public GameState end() {
        throw new ChessException(ResponseCode.GAME_OVER);
    }

    @Override
    public GameState move(Grid grid, Position source, Position target) {
        throw new ChessException(ResponseCode.GAME_OVER);
    }

    @Override
    public GameState status() {
        throw new ChessException(ResponseCode.GAME_OVER);
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
