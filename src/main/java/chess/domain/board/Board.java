package chess.domain.board;

import chess.domain.board.maker.PiecesGenerator;
import chess.domain.piece.Piece;

import java.util.List;

public class Board {

    private final List<Piece> pieces;

    private Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board createBoardWith(final PiecesGenerator piecesGenerator) {
        return new Board(piecesGenerator.generate());
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
