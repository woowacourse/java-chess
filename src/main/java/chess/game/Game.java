package chess.game;

import chess.board.Point;
import chess.piece.Piece;

import java.util.Map;

public class Game {

    private GameState state;

    public Game() {
        state = new Ready();
    }

    public void execute(Command command) {
        state = command.execute(state);
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public Map<Point, Piece> getPointPieces() {
        return state.getPointPieces();
    }
}
