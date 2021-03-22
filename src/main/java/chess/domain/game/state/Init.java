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
    public void moveIfValidColor(Position source, Position target) {
        throw new IllegalStateException("아직 체스판이 준비되지 않았습니다.");
    }

    @Override
    public State end() {
        return new End(board());
    }

    @Override
    public boolean isInit() {
        return true;
    }
}
