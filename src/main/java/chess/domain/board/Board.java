package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.List;

public class Board {
    private final List<Piece> board;

    protected Board(List<Piece> board) {
        this.board = board;
    }
}
