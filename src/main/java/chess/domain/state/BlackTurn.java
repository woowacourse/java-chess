package chess.domain.state;

import chess.domain.piece.Color;

public class BlackTurn implements State {
    private static final BlackTurn BLACK_TURN = new BlackTurn();

    public static BlackTurn getInstance() {
        return BLACK_TURN;
    }

    private BlackTurn() {
    }

    @Override
    public Color color() {
        return Color.BLACK;
    }

    @Override
    public State opposite() {
        return WhiteTurn.getInstance();
    }

    @Override
    public State end() {
        return End.BLACK_WIN;
    }
}
