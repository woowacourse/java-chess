package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Status;
import chess.domain.position.Position;

import java.util.function.ObjDoubleConsumer;

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
        throw new IllegalStateException("게임을 시작해주세요.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public void status(final ObjDoubleConsumer<String> printScore) {
        if (board.isInitialized(new BoardInitializer())) {
            throw new IllegalStateException("게임을 시작해주세요.");
        }
        Status status = new Status(board);
        status.show(printScore);
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
