package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class TestUtils {
    static final Piece WHITE_QUEEN = Piece.from(File.D, Rank.ONE);
    static final Piece BLACK_QUEEN = Piece.from(File.D, Rank.EIGHT);
    static final Piece WHITE_BISHOP = Piece.from(File.C, Rank.ONE);
    static final Piece WHITE_PAWN = Piece.from(File.A, Rank.TWO);
    static final Piece WHITE_ROOK = Piece.from(File.A, Rank.ONE);

    static Map<Square, Piece> createBoard() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            createRow(board, rank);
        }
        return board;
    }

    private static void createRow(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), Piece.from(File.A, Rank.THREE));
        }
    }
}
