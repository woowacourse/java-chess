package chess.board;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.InitPawn;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class BoardInitializer {

    private static final Rank WHITE_PIECES_RANK = Rank.ONE;
    private static final Rank WHITE_PAWN_RANK = Rank.TWO;
    private static final Rank BLACK_PAWN_RANK = Rank.SEVEN;
    private static final Rank BLACK_PIECES_RANK = Rank.EIGHT;

    private static final Map<Position, Piece> pieces = new HashMap<>();

    static {
        createBlackPieces();
        createWhitePieces();
    }

    private BoardInitializer() {
    }

    private static void createWhitePieces() {
        pieces.put(Position.of(File.A, WHITE_PIECES_RANK), new Rook(Color.WHITE));
        pieces.put(Position.of(File.B, WHITE_PIECES_RANK), new Knight(Color.WHITE));
        pieces.put(Position.of(File.C, WHITE_PIECES_RANK), new Bishop(Color.WHITE));
        pieces.put(Position.of(File.D, WHITE_PIECES_RANK), new Queen(Color.WHITE));
        pieces.put(Position.of(File.E, WHITE_PIECES_RANK), new King(Color.WHITE));
        pieces.put(Position.of(File.F, WHITE_PIECES_RANK), new Bishop(Color.WHITE));
        pieces.put(Position.of(File.G, WHITE_PIECES_RANK), new Knight(Color.WHITE));
        pieces.put(Position.of(File.H, WHITE_PIECES_RANK), new Rook(Color.WHITE));
        createPawns(WHITE_PAWN_RANK, Color.WHITE);
    }

    private static void createBlackPieces() {
        pieces.put(Position.of(File.A, BLACK_PIECES_RANK), new Rook(Color.BLACK));
        pieces.put(Position.of(File.B, BLACK_PIECES_RANK), new Knight(Color.BLACK));
        pieces.put(Position.of(File.C, BLACK_PIECES_RANK), new Bishop(Color.BLACK));
        pieces.put(Position.of(File.D, BLACK_PIECES_RANK), new Queen(Color.BLACK));
        pieces.put(Position.of(File.E, BLACK_PIECES_RANK), new King(Color.BLACK));
        pieces.put(Position.of(File.F, BLACK_PIECES_RANK), new Bishop(Color.BLACK));
        pieces.put(Position.of(File.G, BLACK_PIECES_RANK), new Knight(Color.BLACK));
        pieces.put(Position.of(File.H, BLACK_PIECES_RANK), new Rook(Color.BLACK));
        createPawns(BLACK_PAWN_RANK, Color.BLACK);
    }

    private static void createPawns(Rank rank, Color color) {
        for (File file : File.values()) {
            pieces.put(Position.of(file, rank), new InitPawn(color));
        }
    }

    public static Board createBoard() {
        return new Board(pieces);
    }
}
