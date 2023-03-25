package chess.domain.state;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;

public class End extends State {
    protected End(final ChessGame ChessGame) {
        super(ChessGame);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isGameEnd() {
        return true;
    }

    @Override
    public State move(final Position source, final Position target) {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }

    @Override
    public double calculateScore(Color color) {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }

    @Override
    public Color getColor() {
        throw new UnsupportedOperationException("종료 후에 사용할 수 없는 명령어 입니다.");
    }
}
