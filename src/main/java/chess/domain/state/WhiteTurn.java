package chess.domain.state;

import chess.domain.piece.Color;

public class WhiteTurn implements State {
    private static final WhiteTurn WHITE_TURN = new WhiteTurn();

    public static WhiteTurn getInstance() {
        return WHITE_TURN;
    }

    private WhiteTurn() {
    }

    @Override
    public Color color() {
        return Color.WHITE;
    }

    @Override
    public State opposite() {
        return BlackTurn.getInstance();
    }

    @Override
    public State end() {
        return End.WHITE_WIN;
    }
}
