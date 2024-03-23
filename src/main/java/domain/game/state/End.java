package domain.game.state;

import domain.board.ChessBoard;
import domain.position.Position;

public class End extends GameState {
    protected End(ChessBoard board) {
        super(board);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("게임이 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
