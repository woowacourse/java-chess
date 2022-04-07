package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import console.controller.Request;

import java.util.List;
import java.util.Map;

public class Game {

    private final Board board;
    private Color turnColor;

    public Game(Board board, Color turnColor) {
        this.board = board;
        this.turnColor = turnColor;
    }

    public boolean isRunnable() {
        return !board.isKingDead(Color.WHITE) && !board.isKingDead(Color.BLACK);
    }

    public void move(List<Point> arguments) {
        if (!isRunnable()) {
            throw new UnsupportedOperationException("[ERROR] 킹이 죽어 말을 움직일 수 없습니다.");
        }
        Request.validateArgumentSize(arguments);
        board.move(arguments.get(0), arguments.get(1), turnColor);
        turnColor = turnColor.toggle();
    }

    public Map<Point, Piece> getPointPieces() {
        return board.getPointPieces();
    }

    public Color getTurnColor() {
        return turnColor;
    }

    public Map<Color, Double> calculateScore() {
        return board.calculateScore();
    }
}
