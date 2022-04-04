package chess.domain.game;

import java.util.List;
import java.util.Map;

import chess.controller.Arguments;
import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Piece;

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

    public Map<Point, Piece> getPointPieces() {
        return board.getPointPieces();
    }

    public Color getColor() {
        return turnColor;
    }

    public Map<Color, Double> getColorScore() {
        return board.calculateScore();
    }
}
