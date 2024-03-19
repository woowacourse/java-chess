package chess.board;

import chess.piece.Color;
import chess.piece.ColoredPiece;
import chess.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class BoardInitializer {

    private static final int WHITE_PIECES_RANK = 1;
    private static final int WHITE_PAWN_RANK = 2;
    private static final int BLACK_PAWN_RANK = 7;
    private static final int BLACK_PIECES_RANK = 8;

    private static final Map<Position, ColoredPiece> pieces = new HashMap<>();

    static {
        createBlackPieces();
        createWhitePieces();
    }

    private BoardInitializer() {
    }

    private static void createWhitePieces() {
        pieces.put(Position.of("a", WHITE_PIECES_RANK), new ColoredPiece(Piece.ROOK, Color.WHITE));
        pieces.put(Position.of("b", WHITE_PIECES_RANK), new ColoredPiece(Piece.KNIGHT, Color.WHITE));
        pieces.put(Position.of("c", WHITE_PIECES_RANK), new ColoredPiece(Piece.BISHOP, Color.WHITE));
        pieces.put(Position.of("d", WHITE_PIECES_RANK), new ColoredPiece(Piece.QUEEN, Color.WHITE));
        pieces.put(Position.of("e", WHITE_PIECES_RANK), new ColoredPiece(Piece.KING, Color.WHITE));
        pieces.put(Position.of("f", WHITE_PIECES_RANK), new ColoredPiece(Piece.BISHOP, Color.WHITE));
        pieces.put(Position.of("g", WHITE_PIECES_RANK), new ColoredPiece(Piece.KNIGHT, Color.WHITE));
        pieces.put(Position.of("h", WHITE_PIECES_RANK), new ColoredPiece(Piece.ROOK, Color.WHITE));
        createPawns(WHITE_PAWN_RANK, Color.WHITE);
    }

    private static void createBlackPieces() {
        pieces.put(Position.of("a", BLACK_PIECES_RANK), new ColoredPiece(Piece.ROOK, Color.BLACK));
        pieces.put(Position.of("b", BLACK_PIECES_RANK), new ColoredPiece(Piece.KNIGHT, Color.BLACK));
        pieces.put(Position.of("c", BLACK_PIECES_RANK), new ColoredPiece(Piece.BISHOP, Color.BLACK));
        pieces.put(Position.of("d", BLACK_PIECES_RANK), new ColoredPiece(Piece.QUEEN, Color.BLACK));
        pieces.put(Position.of("e", BLACK_PIECES_RANK), new ColoredPiece(Piece.KING, Color.BLACK));
        pieces.put(Position.of("f", BLACK_PIECES_RANK), new ColoredPiece(Piece.BISHOP, Color.BLACK));
        pieces.put(Position.of("g", BLACK_PIECES_RANK), new ColoredPiece(Piece.KNIGHT, Color.BLACK));
        pieces.put(Position.of("h", BLACK_PIECES_RANK), new ColoredPiece(Piece.ROOK, Color.BLACK));
        createPawns(BLACK_PAWN_RANK, Color.BLACK);
    }

    private static void createPawns(int rankNumber, Color color) {
        for (File file : File.values()) {
            pieces.put(new Position(file, Rank.from(rankNumber)), new ColoredPiece(Piece.PAWN, color));
        }
    }

    public static Board createBoard() {
        return new Board(pieces);
    }
}
