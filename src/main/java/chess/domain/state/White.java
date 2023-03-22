package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.dto.ChessInputDto;

public final class White extends State {
    public White(final Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new Start(Board.create());
    }

    @Override
    public State move(final ChessInputDto dto) {
        board.move(dto.getSource(), dto.getTarget(), Color.WHITE);
        return new Black(board);
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
