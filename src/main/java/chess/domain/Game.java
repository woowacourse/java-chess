package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import java.util.List;

public class Game {
    private final Board board;
    private Camp turn;

    public Game() {
        this.board = new Board();
        turn = Camp.WHITE;
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public void move(final Square source, final Square target) {
        if (board.isNotMyTurn(source, turn) || board.isEmptyPiece(source)) {
            throw new IllegalArgumentException("자신의 말만 이동할 수 있습니다.");
        }
        board.move(source, target);
        turn = turn.nextTurn(turn);
    }
}
