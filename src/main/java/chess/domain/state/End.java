package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;

public class End implements State {

    private final Board board;

    public End(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public State move(Location source, Location target) {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public TeamScore getScore() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }
}
