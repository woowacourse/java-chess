package chess.domain;

import chess.domain.board.Board;

public class ChessGame {

    private final Board board;

    public ChessGame() {
        this.board = Board.create();
    }

    public Board getBoard() {
        return board;
    }

    public void movePiece(Position current, Position target) {
        board.movePiece(current, target);
    }
}
