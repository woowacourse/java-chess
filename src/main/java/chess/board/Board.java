package chess.board;

import chess.piece.Piece;

import java.util.Map;

public class Board {

    private final Map<Point, Piece> pointPieces;

    private Board(Map<Point, Piece> pointPieces) {
        this.pointPieces = pointPieces;
    }

    public static Board of(BoardGenerator generator) {
        return new Board(generator.generate());
    }
}
