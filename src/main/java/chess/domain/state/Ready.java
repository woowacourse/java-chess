package chess.domain.state;

import chess.domain.Board;
import chess.domain.position.Position;

public class Ready extends State {

    @Override
    public State start() {
        return new WhiteTurn(new Board());
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("게임을 시작해 주세요.");
    }
}
