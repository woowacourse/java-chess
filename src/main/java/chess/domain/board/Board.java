package chess.domain.board;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import chess.domain.piece.Piece;

import java.util.Map;

public class Board {
    private final Map<Square, Piece> board;

    Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public Piece findPiece(final File file, final Rank rank) {
        return board.get(Square.of(file, rank));
    }
}
