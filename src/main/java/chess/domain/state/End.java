package chess.domain.state;

import chess.domain.Board;
import chess.dto.ChessInputDto;

public class End extends State {
    public End(final Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(final ChessInputDto dto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }
}
