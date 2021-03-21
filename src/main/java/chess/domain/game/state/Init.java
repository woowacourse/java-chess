package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public class Init extends Preparing {

    public Init(Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new WhiteTurn(board());
    }

    @Override
    public State move(Position source, Position target) {
        throw new IllegalStateException();
    }

    @Override
    public State end() {
        throw new IllegalStateException();
    }
}
