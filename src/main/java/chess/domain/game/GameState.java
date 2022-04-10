package chess.domain.game;

import java.util.Map;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Piece;
import chess.dto.Arguments;

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

    public abstract GameState move(Arguments arguments);

    public abstract String getState();

    public Map<Point, Piece> getPointPieces() {
        return board.getPointPieces();
    }

    public String getColor() {
        return turnColor.name();
    }

    public Map<Color, Double> getColorScore() {
        return board.calculateScore();
    }
}
