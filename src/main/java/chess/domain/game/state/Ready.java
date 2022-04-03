package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Ready implements State {

    private final Board board;

    public Ready(final Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new Started(Color.WHITE, board);
    }

    @Override
    public State end() {
        return new Ended(board);
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("[ERROR] start를 하지 않아 move 할 수 없습니다.");
    }

    @Override
    public State status() {
        throw new IllegalStateException("[ERROR] start를 하지 않아 status 할 수 없습니다.");
    }

    @Override
    public boolean isNotEnded() {
        return true;
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
