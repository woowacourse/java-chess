package chess;

import static chess.domain.piece.Color.WHITE;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public final class ChessGame {

    private final Board board;
    private Color turn;

    public ChessGame(final Board board) {
        this.board = board;
        this.turn = WHITE;
    }

    public boolean isEnd() {
        return board.isEnd();
    }

    public Color winner() {
        return board.winner();
    }

    public Map<Position, Piece> board() {
        return board.board();
    }

    public void move(final Position from, final Position to) {
        board.move(from, to, turn);
        turn = turn.opposite();
    }

    public Map<Color, Double> calculateScore() {
        return board.calculateScore();
    }
}
