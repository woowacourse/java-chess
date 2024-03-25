package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;
    private Side currentSide = Side.WHITE;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(Position source, Position target) {
        checkTurn(source);
        board.move(source, target);
    }

    private void checkTurn(Position source) {
        Piece piece = board.findPiece(source);
        if (piece.isNotSame(currentSide)) {
            throw new IllegalArgumentException("해당 진영의 차례가 아닙니다.");
        }
        currentSide = currentSide.opponent();
    }
}
