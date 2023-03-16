package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Position;

public class Black extends State {
    protected Black(final Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State move(final Position source, final Position target) {
        final Board newBoard = board.move(source, target, Color.BLACK);
        return new White(newBoard);
    }

    @Override
    public State start() {
        return new Ready(Board.create());
    }

    @Override
    public State end() {
        return new End(board);
    }
}
