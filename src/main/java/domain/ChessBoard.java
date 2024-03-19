package domain;

import domain.piece.Piece;
import domain.piece.PiecesGenerator;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(final PiecesGenerator piecesGenerator) {
        this(piecesGenerator.generate());
    }

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }
}
