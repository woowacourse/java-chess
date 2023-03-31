package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Position;

public class Running implements GameState {

    private static Running running;

    private Running() {
    }

    public static Running getState() {
        if (running == null) {
            running = new Running();
        }
        return running;
    }

    @Override
    public GameState start() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 실행중입니다.");
    }

    @Override
    public void progress(Position source, Position target, Board board) {
        board.move(source, target);
    }

    @Override
    public boolean isNotTerminated() {
        return true;
    }

    @Override
    public double calculateBlackScore(Board board) {
        throw new IllegalArgumentException("[ERROR] 게임이 실행중 일 때는 결과를 알 수 없습니다.");
    }

    @Override
    public double calculateWhiteScore(Board board) {
        throw new IllegalArgumentException("[ERROR] 게임이 실행중 일 때는 결과를 알 수 없습니다.");
    }
}
