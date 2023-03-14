package chess.domain.board;

import chess.domain.board.position.PiecePosition;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Staunton;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory {

    private static final Map<PiecePosition, Piece> whitePieces = new HashMap<>();
    private static final Map<PiecePosition, Piece> blackPieces = new HashMap<>();

    static {
        createExcludePawn(whitePieces, 1, Color.WHITE);
        createExcludePawn(blackPieces, 8, Color.BLACK);
        createPawns(whitePieces, 2, Color.WHITE);
        createPawns(blackPieces, 7, Color.BLACK);
    }

    private static void createPawns(final Map<PiecePosition, Piece> pieces, final int rank, final Color color) {
        for (char file = 'a'; file <= 'h'; file++) {
            pieces.put(PiecePosition.of(rank, file), new Piece(Staunton.PAWN, color));
        }
    }

    private static void createExcludePawn(final Map<PiecePosition, Piece> pieces, final int rank, final Color color) {
        pieces.put(PiecePosition.of(rank, 'a'), new Piece(Staunton.ROOK, color));
        pieces.put(PiecePosition.of(rank, 'b'), new Piece(Staunton.KNIGHT, color));
        pieces.put(PiecePosition.of(rank, 'c'), new Piece(Staunton.BISHOP, color));
        pieces.put(PiecePosition.of(rank, 'd'), new Piece(Staunton.QUEEN, color));
        pieces.put(PiecePosition.of(rank, 'e'), new Piece(Staunton.KING, color));
        pieces.put(PiecePosition.of(rank, 'f'), new Piece(Staunton.BISHOP, color));
        pieces.put(PiecePosition.of(rank, 'g'), new Piece(Staunton.KNIGHT, color));
        pieces.put(PiecePosition.of(rank, 'h'), new Piece(Staunton.ROOK, color));
    }

    public static Map<PiecePosition, Piece> create() {
        final HashMap<PiecePosition, Piece> piecePositionStauntonHashMap = new HashMap<>(whitePieces);
        piecePositionStauntonHashMap.putAll(new HashMap<>(blackPieces));
        return piecePositionStauntonHashMap;
    }
}
