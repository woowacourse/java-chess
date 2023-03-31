package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Position;

public class Ready implements GameState {

    private static Ready ready;

    private Ready() {
    }

    public static Ready getState() {
        if (ready == null) {
            ready = new Ready();
        }
        return ready;
    }

    @Override
    public GameState start() {
        return Running.getState();
    }

    @Override
    public void progress(Position source, Position target, Board board) {
        throw new IllegalArgumentException("[ERROR] 게임을 먼저 시작해주세요.");
    }

    @Override
    public boolean isNotTerminated() {
        return true;
    }

    @Override
    public double calculateBlackScore(Board board) {
        throw new IllegalArgumentException("[ERROR] 게임을 먼저 시작해주세요.");
    }

    @Override
    public double calculateWhiteScore(Board board) {
        throw new IllegalArgumentException("[ERROR] 게임을 먼저 시작해주세요.");
    }
}
