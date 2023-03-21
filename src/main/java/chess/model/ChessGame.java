package chess.model;

import chess.model.board.Board;
import chess.model.board.Turn;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private final Turn turn;

    public ChessGame() {
        this.board = Board.create();
        this.turn = new Turn();
    }

    public void move(final Position source, final Position target) {
        try {
            board.move(source, target, turn.findNextPlayer());
        } catch (final IllegalArgumentException e) {
            turn.beforePlayer();
            throw e;
        }
    }

    public Map<Position, Piece> getBoard() {
        return board.getSquares();
    }
}
