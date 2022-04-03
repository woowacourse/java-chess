package chess.domain.game.state;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Ended implements State {

    @Override
    public State start() {
        throw new IllegalStateException("[ERROR] 게임이 끝나 start 할 수 없습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("[ERROR] 게임이 끝나 end 할 수 없습니다.");
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("[ERROR] 게임이 끝나 move 할 수 없습니다.");
    }

    @Override
    public State status() {
        throw new IllegalStateException("[ERROR] 게임이 끝나 status 할 수 없습니다.");
    }

    @Override
    public boolean isNotEnded() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public Color getTurn() {
        return Color.NONE;
    }
}
