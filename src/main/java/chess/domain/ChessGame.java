package chess.domain;

import chess.domain.location.Location;
import chess.domain.state.Ready;
import chess.domain.state.State;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public void move(Location source, Location target) {
        this.state = state.move(source, target);
    }

    public void start() {
        if (isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 실행 중 입니다.");
        }
        this.state = state.start();
    }

    public void status() {
        if (!isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 실행 중일 때만 점수를 출력할 수 있습니다.");
        }
    }

    public void end() {
        if (!isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
        }
        this.state = state.end();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public TeamScore getScore() {
        return state.getScore();
    }
}
