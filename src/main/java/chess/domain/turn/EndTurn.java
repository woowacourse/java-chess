package chess.domain.turn;

import chess.domain.Color;

public class EndTurn implements GameTurn {

    @Override
    public GameTurn nextTurn() {
        return null;
    }

    @Override
    public Color color() {
        return null;
    }
}
