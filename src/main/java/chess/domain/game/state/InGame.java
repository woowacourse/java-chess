package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class InGame implements GameState {

    private final Board board;

    protected InGame(Board board) {
        this.board = board;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException("이미 게임 중 이므로 게임을 시작할 수 없습니다.");
    }

    @Override
    public GameState status() {
        return new ReadyToStart();
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
