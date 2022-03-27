package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.position.Position;

public class Ready extends State {

    @Override
    public State start() {
        return new WhiteTurn(new Board(new BoardInitializer()));
    }

    @Override
    public State end() {
        return new End(board);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("게임을 시작해 주세요.");
    }

    @Override
    public Status status() {
        throw new IllegalStateException("게임이 끝나지 않았습니다.");
    }
}
