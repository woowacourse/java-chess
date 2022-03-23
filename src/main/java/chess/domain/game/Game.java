package chess.domain.game;

import chess.controller.api.Request;
import chess.domain.board.Point;
import chess.domain.piece.Piece;

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
