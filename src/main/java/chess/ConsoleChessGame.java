package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class ConsoleChessGame {
    private final Board board;
    private Color currentTurn;

    public ConsoleChessGame(Board board, Color turn) {
        this.board = board;
        this.currentTurn = turn;
    }

    public ConsoleChessGame() {
        this(BoardFactory.newInstance(), Color.WHITE);
    }

    public Map<Position, Piece> board() {
        return new HashMap<>(board.getBoard());
    }

    public void move(Position from, Position to) {
        board.move(from, to);
        currentTurn = currentTurn.opposite();
    }

    public Map<Color, Double> score() {
        return board.getScore();
    }

    public boolean isFinished() {
        return board.isFinished();
    }
}
