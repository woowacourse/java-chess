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
        // TODO: 점수 및 결과 출력
        // TODO: 게임 시작 전이면 점수 출력할 수 없다고 안내

        // TODO: 게임 종료 후에는 점수 출력
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
