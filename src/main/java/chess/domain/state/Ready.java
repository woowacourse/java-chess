package chess.domain.state;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;

public final class Ready extends State {
    public Ready(final ChessGame ChessGame) {
        super(ChessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public State move(final Position source, final Position target) {
        throw new UnsupportedOperationException("start 명령어를 먼저 입력해 주세요");
    }

    @Override
    public State start() {
        return new Running(chessGame);
    }

    @Override
    public State end() {
        return new End(chessGame);
    }

    @Override
    public double calculateScore(Color color) {
        throw new UnsupportedOperationException("start 명령어를 먼저 입력해 주세요");
    }

    @Override
    public Color getColor() {
        return Color.EMPTY;
    }
}
