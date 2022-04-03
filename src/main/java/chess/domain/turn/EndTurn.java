package chess.domain.turn;

import chess.domain.Color;

public class EndTurn implements GameTurn {

    private final Color color;

    public EndTurn(Color color) {
        this.color = color;
    }

    @Override
    public GameTurn nextTurn() {
        throw new IllegalStateException("종료된 게임은 다음 턴이 없습니다.");
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
