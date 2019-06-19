package chess.domain;

import chess.domain.pieces.Empty;
import chess.domain.pieces.Piece;

import java.util.Map;

public class Board {
    private Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public void play(Point p1, Point p2, ChessTeam team) {
        if (!board.get(p1).isTurn(team)) {
            throw new IllegalArgumentException();
        }
        if (board.get(p1).isAlly(board.get(p2))) {
            throw new IllegalArgumentException();
        }
        if (board.get(p2).equals(new Empty())) {
            move(p1, p2);
            return;
        }
        attack(p1, p2);

    }

    public void play(Point p1, Point p2) {
        if (board.get(p1).isAlly(board.get(p2))) {
            throw new IllegalArgumentException();
        }
        if (board.get(p2).equals(new Empty())) {
            move(p1, p2);
            return;
        }
        attack(p1, p2);
    }

    private void attack(Point p1, Point p2) {
        Direction direction = board.get(p1).attack(p1, p2);
        checkRange(p1, p2, direction);

    }

    private void checkRange(Point p1, Point p2, Direction direction) {
        Piece piece = board.get(p1);
        Point point = p1.transport(direction);
        while (!point.equals(p2)) {
            if (!board.get(point).equals(new Empty())) {
                throw new IllegalArgumentException();
            }
            point = point.transport(direction);
        }
        board.put(p2, piece);
        board.put(p1, new Empty());
    }

    private void move(Point p1, Point p2) {
        Direction direction = board.get(p1).move(p1, p2);
        checkRange(p1, p2, direction);
    }

    public Piece get(Point point) {
        return board.get(point);
    }
}
