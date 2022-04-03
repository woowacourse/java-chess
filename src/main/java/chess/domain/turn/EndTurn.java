package chess.domain.turn;

import chess.domain.Color;

public class EndTurn implements GameTurn {

    @Override
    public GameTurn nextTurn() {
        throw new IllegalStateException("종료된 게임은 다음 턴이 없습니다.");
    }

    @Override
    public Color color() {
        return null;
    }
}
