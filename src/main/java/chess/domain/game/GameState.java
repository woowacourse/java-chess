package chess.domain.game;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.Color;
import chess.dto.Response;

public abstract class GameState {

    protected Board board;
    protected final Color turnColor;

    public GameState(Board board, Color turnColor) {
        this.board = board;
        this.turnColor = turnColor;
    }

    public abstract GameState start();

    public abstract GameState finish();

    public abstract boolean isRunnable();

    public abstract GameState move(List<String> arguments);

    public abstract GameState status();

    public abstract Response getResponse();
}
