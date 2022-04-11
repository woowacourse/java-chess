package chess;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.None;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.mulitiplemovepiece.Bishop;
import chess.domain.piece.mulitiplemovepiece.Queen;
import chess.domain.piece.mulitiplemovepiece.Rook;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class TestUtils {
    public static final Piece WHITE_QUEEN = new Queen(Color.WHITE);
    public static final Piece BLACK_QUEEN = new Queen(Color.BLACK);
    public static final Piece WHITE_BISHOP = new Bishop(Color.WHITE);
    public static final Piece WHITE_PAWN = new Pawn(Color.WHITE);
    public static final Piece WHITE_ROOK = new Rook(Color.WHITE);

    public static Map<Square, Piece> createBlankBoard() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            createRow(board, rank);
        }
        return board;
    }

    private static void createRow(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), new None(Color.NONE));
        }
    }
}
