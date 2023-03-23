package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public static ChessGame create() {
        return new ChessGame(BoardGenerator.createBoard());
    }

    public void movePiece(Position from, Position to) {
        board.movePiece(from, to);
    }

    public List<List<Piece>> getBoard() {
        return board.getBoard();
    }

}
