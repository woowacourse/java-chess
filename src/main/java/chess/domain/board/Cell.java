package chess.domain.board;


import chess.domain.piece.Piece;

public final class Cell {
    private final Piece piece;

    // 인스턴스화 방지
    private Cell(Piece piece) {
        this.piece = piece;
    }

    public static Cell from(Piece piece) {
        return new Cell(piece);
    }
}
