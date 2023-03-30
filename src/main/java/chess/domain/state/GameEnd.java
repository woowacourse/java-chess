package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Position;

public final class GameEnd extends State {
    GameEnd(final Color color) {
        super(color);
    }

    @Override
    public State move(final Position source, final Position target, final Board board) {
        throw new UnsupportedOperationException("게임이 종료되었습니다. start, status, end 명령어를 입력해주세요.");
    }

    @Override
    public State start() {
        return new Running(Color.EMPTY);
    }

    @Override
    public State end() {
        return new End(color());
    }
}
