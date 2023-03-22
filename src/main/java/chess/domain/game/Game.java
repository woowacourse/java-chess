package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.exception.CampNotMatchException;
import java.util.List;

public class Game {
    private final Board board;
    private Camp turn;

    public Game() {
        this.board = new Board();
        this.turn = Camp.WHITE;
    }

    public List<Piece> getPieces() {
        return this.board.getPieces();
    }

    public void move(final Square source, final Square target) {
        if (this.board.isNotMyTurn(source, this.turn) || this.board.isEmptyPiece(source)) {
            throw new CampNotMatchException(this.turn);
        }
        this.board.move(source, target);
        this.turn = this.turn.nextTurn(this.turn);
    }
}
