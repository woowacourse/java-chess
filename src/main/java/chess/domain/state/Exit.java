package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public final class Exit extends State {

    private static final String EXIT_MESSAGE = "게임이 종료됐습니다.";

    Exit() {
    }

    @Override
    public State start() {
        throw new IllegalStateException(EXIT_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException(EXIT_MESSAGE);
    }

    @Override
    public double score(final Color color) {
        throw new IllegalStateException(EXIT_MESSAGE);
    }

    @Override
    public Result getWinner() {
        return Result.EMPTY;
    }
}
