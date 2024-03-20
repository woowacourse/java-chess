package chess.board;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.InitPawn;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardInitializer {

    private static final int WHITE_PIECES_RANK = 1;
    private static final int WHITE_PAWN_RANK = 2;
    private static final int BLACK_PAWN_RANK = 7;
    private static final int BLACK_PIECES_RANK = 8;

    private static final Map<Position, Piece> pieces = new HashMap<>();

    static {
        createBlackPieces();
        createWhitePieces();
    }

    private BoardInitializer() {
    }

    private static void createWhitePieces() {
        pieces.put(Position.of("a", WHITE_PIECES_RANK), new Rook(Color.WHITE));
        pieces.put(Position.of("b", WHITE_PIECES_RANK), new Knight(Color.WHITE));
        pieces.put(Position.of("c", WHITE_PIECES_RANK), new Bishop(Color.WHITE));
        pieces.put(Position.of("d", WHITE_PIECES_RANK), new Queen(Color.WHITE));
        pieces.put(Position.of("e", WHITE_PIECES_RANK), new King(Color.WHITE));
        pieces.put(Position.of("f", WHITE_PIECES_RANK), new Bishop(Color.WHITE));
        pieces.put(Position.of("g", WHITE_PIECES_RANK), new Knight(Color.WHITE));
        pieces.put(Position.of("h", WHITE_PIECES_RANK), new Rook(Color.WHITE));
        createPawns(WHITE_PAWN_RANK, Color.WHITE);
    }

    private static void createBlackPieces() {
        pieces.put(Position.of("a", BLACK_PIECES_RANK), new Rook(Color.BLACK));
        pieces.put(Position.of("b", BLACK_PIECES_RANK), new Knight(Color.BLACK));
        pieces.put(Position.of("c", BLACK_PIECES_RANK), new Bishop(Color.BLACK));
        pieces.put(Position.of("d", BLACK_PIECES_RANK), new Queen(Color.BLACK));
        pieces.put(Position.of("e", BLACK_PIECES_RANK), new King(Color.BLACK));
        pieces.put(Position.of("f", BLACK_PIECES_RANK), new Bishop(Color.BLACK));
        pieces.put(Position.of("g", BLACK_PIECES_RANK), new Knight(Color.BLACK));
        pieces.put(Position.of("h", BLACK_PIECES_RANK), new Rook(Color.BLACK));
        createPawns(BLACK_PAWN_RANK, Color.BLACK);
    }

    private static void createPawns(int rankNumber, Color color) {
        for (File file : File.values()) {
            pieces.put(new Position(file, Rank.from(rankNumber)), new InitPawn(color));
        }
    }

    public static Board createBoard() {
        return new Board(pieces);
    }
}
