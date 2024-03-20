package chess.domain.chessboard;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

public class Chessboard {

    private final Map<Square, Piece> chessboard;

    private Chessboard(final Map<Square, Piece> chessboard) {
        this.chessboard = chessboard;
    }

    public static Chessboard create() {
        Map<Square, Piece> chessboard = new HashMap<>();
        Set<Piece> pieces = new HashSet<>();
        pieces.addAll(createPieces(WHITE));
        pieces.addAll(createPieces(BLACK));
        pieces.forEach(piece -> putPieces(chessboard, piece));
        return new Chessboard(chessboard);
    }

    private static void putPieces(final Map<Square, Piece> chessboard, final Piece piece) {
        PieceType pieceType = piece.getPieceType();
        for (int index = 0; index < pieceType.getCount(); index++) {
            Square square = pieceType.startSquareOf(piece.getColor(), index);
            chessboard.put(square, piece);
        }
    }

    private static Set<Piece> createPieces(final Color color) {
        Set<Piece> pieces = new HashSet<>();
        pieces.add(new King(color));
        pieces.add(new Queen(color));
        pieces.add(new Bishop(color));
        pieces.add(new Knight(color));
        pieces.add(new Rook(color));
        pieces.add(new StartingPawn(color));
        return pieces;
    }

    public Map<Square, Piece> getChessboard() {
        return Map.copyOf(chessboard);
    }
}
