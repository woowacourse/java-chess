package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.position.Position;

public class Ready extends State {

    public Ready() {
        this(new Board(new BoardInitializer()));
    }

    Ready(final Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new WhiteTurn(board);
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("게임을 시작해 주세요.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public void status() {
        if (board.isInitialized(new BoardInitializer())) {
            throw new IllegalStateException("게임을 시작해주세요.");
        }
//        board.showStatus();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isEnded() {
        return false;
    }
}
