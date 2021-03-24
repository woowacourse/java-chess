package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.game.Side;

public abstract class GameState implements State {

    private final Board board;
    private final Side side;

    public GameState(Board board, Side side) {
        this.board = board;
        this.side = side;
    }

    @Override
    public final Board board() {
        return board;
    }

    @Override
    public final Side currentTurn() {
        return side;
    }

    public final Side changeTurn() {
        return side.changeTurn();
    }
}
