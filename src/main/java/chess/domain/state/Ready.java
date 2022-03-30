package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.notation.Color;
import chess.domain.position.Position;

public final class Ready extends State {

    private static final String ERROR_MESSAGE = "게임을 시작해 주세요.";

    @Override
    public State start() {
        return new Running(Color.WHITE, new Board(new BoardInitializer()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException(ERROR_MESSAGE);
    }

    @Override
    public Status status() {
        throw new IllegalStateException(ERROR_MESSAGE);
    }
}
