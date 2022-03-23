package chess.game;

import chess.board.Point;
import chess.controller.api.Request;
import chess.piece.Piece;

import java.util.Map;

public class Game {

    private GameState state;

    public Game() {
        state = new Ready();
    }

    public void execute(Request request) {
        Command command = Command.find(request.getCommand());
        state = command.execute(state, request.getArguments());
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public Map<Point, Piece> getPointPieces() {
        return state.getPointPieces();
    }
}
