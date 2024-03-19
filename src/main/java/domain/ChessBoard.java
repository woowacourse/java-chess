package domain;

import domain.piece.Piece;
import domain.piece.PiecesGenerator;
import dto.BoardStatus;

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

    public BoardStatus status() {
        return BoardStatus.from(board);
    }
}
