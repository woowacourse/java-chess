package chess.model;

import chess.model.board.Board;
import chess.model.unit.Piece;
import chess.model.unit.Side;

import java.util.HashSet;

public class ChessGame {
    private static final int DEFAULT_KING_COUNT = 2;

    private Board board;
    private Side turn;
    private MoveHandler handler;

    public ChessGame(final Board board, final Side side) {
        this.board = board;
        turn = side;
        handler = new MoveHandler(board);
    }

    public boolean move(final Square beginSquare, final Square endSquare) {
        return handler.move(beginSquare, endSquare, turn);
    }

    public boolean isKingAlive() {
        return new HashSet<>(board.getPieces(Piece::isKing)).size() == DEFAULT_KING_COUNT;
    }
}
