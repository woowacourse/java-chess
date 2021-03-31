package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Team;

public class Finished implements GameState {
    private Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public GameState start() {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Override
    public GameState end() {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Override
    public GameState move() {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Override
    public GameState status() {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Override
    public Board board() {
        throw new IllegalStateException("체스 보드를 불러 올 수 없는 상태입니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public String winner() {
        return board.winner();
    }
}
