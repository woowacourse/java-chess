package chess.domain.game;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.state.GameState;
import chess.domain.game.state.Waiting;
import chess.domain.piece.Piece;
import chess.domain.game.state.position.Position;
import chess.domain.piece.property.Color;

public class ChessGame {
    private GameState state = new Waiting();

    public Map<Position, Piece> start() {
        state = state.start();
        return state.getBoard();
    }

    public Map<Position, Piece> move(Position source, Position target) {
        state = state.move(source, target);
        return state.getBoard();
    }

    public Map<Position, Piece> end() {
        state = state.end();
        return new HashMap<>();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Map<Color, Double> status() {
        return state.status();
    }
}
