package chess.domain.state;

import chess.domain.Board;
import chess.dto.ChessInputDto;

public final class Start extends State {
    public Start(final Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new White(board);
    }

    @Override
    public State move(final ChessInputDto dto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        return new End(board);
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
