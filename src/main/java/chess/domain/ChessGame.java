package chess.domain;

import chess.domain.move.MoveChecker;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class ChessGame {

    private Color turn;
    private final Board board;

    public ChessGame(final Color turn, final Board board) {
        this.turn = turn;
        this.board = board;
    }

    public boolean move(Position from, Position to) {
        final MoveChecker moveChecker = new MoveChecker(board, turn);
        moveChecker.checkMovable(from, to);

        if (moveChecker.isCheckmate(to)) {
            return checkmate();
        }
        return movePiece(from, to);
    }

    private boolean checkmate() {
        return false;
    }

    private boolean movePiece(final Position from, final Position to) {
        board.move(from, to);
        turn = turn.getOpposite();
        return true;
    }

    public String getTurn() {
        return turn.getName();
    }

    public Board getBoard() {
        return board;
    }
}
