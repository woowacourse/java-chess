package domain.game;

import domain.game.state.BlackTurn;
import domain.game.state.GameState;
import domain.game.state.WhiteTurn;

public class TestableChessGame extends ChessGame {
    public TestableChessGame(GameState state, Board board) {
        super(state, board);
    }

    public static TestableChessGame whiteTurnOf(Board board) {
        return new TestableChessGame(WhiteTurn.getInstance(), board);
    }

    public static TestableChessGame blackTurnOf(Board board) {
        return new TestableChessGame(BlackTurn.getInstance(), board);
    }
}
