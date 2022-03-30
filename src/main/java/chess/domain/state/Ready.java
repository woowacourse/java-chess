package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Color;

public class Ready implements State {

    @Override
    public State start() {
        final Board board = Board.create();
        return new Started(Color.WHITE, board);
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(final String[] commands) {
        throw new IllegalStateException("[ERROR] start를 하지 않아 move 할 수 없습니다.");
    }

    @Override
    public State status() {
        throw new IllegalStateException("[ERROR] start를 하지 않아 status 할 수 없습니다.");
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public Color getColor() {
        throw new UnsupportedOperationException("[ERROR] 아직 게임을 시작하지 않았습니다.");
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("[ERROR] 아직 게임을 시작하지 않았습니다.");
    }

}
