package chess.domain.state;

import chess.domain.piece.Color;

public class Finish extends AbstractState {

    public Finish(Color color) {
        super(color);
    }

    @Override
    public State nextState() {
        throw new IllegalStateException("새로운 게임을 시작해주세요");
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public void nextTurn() {
    }

    @Override
    public Color winner() {
        return color;
    }

    @Override
    public void validateMove() {
        throw new IllegalArgumentException("게임진행중이 아닙니다.");
    }
}
