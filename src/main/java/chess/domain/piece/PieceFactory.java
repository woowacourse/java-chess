package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PieceFactory {
    private static final int KING_SIZE = 1;
    private static final int QUEEN_SIZE = 1;
    private static final int BISHOP_SIZE = 2;
    private static final int KNIGHT_SIZE = 2;
    private static final int ROOK_SIZE = 2;
    private static final int PAWN_SIZE = 8;
    private static final String KING = "k";
    private static final String QUEEN = "q";
    private static final String BISHOP = "b";
    private static final String KNIGHT = "n";
    private static final String ROOK = "r";
    private static final String PAWN = "p";

    public static List<Piece> initializeWhitePiece() {
        List<Piece> pieces = new ArrayList<>();
        IntStream.range(0, KING_SIZE)
                .forEach(count -> pieces.add(generateKing(KING)));
        IntStream.range(0, QUEEN_SIZE)
                .forEach(count -> pieces.add(generateQueen(QUEEN)));
        IntStream.range(0, BISHOP_SIZE)
                .forEach(count -> pieces.add(generateBishop(BISHOP)));
        IntStream.range(0, KNIGHT_SIZE)
                .forEach(count -> pieces.add(generateKnight(KNIGHT)));
        IntStream.range(0, ROOK_SIZE)
                .forEach(count -> pieces.add(generateRook(ROOK)));
        IntStream.range(0, PAWN_SIZE)
                .forEach(count -> pieces.add(generatePawn(PAWN)));
        return new ArrayList<>(pieces);
    }

    public static List<Piece> initializeBlackPiece() {
        List<Piece> pieces = new ArrayList<>();
        IntStream.range(0, KING_SIZE)
                .forEach(count -> pieces.add(generateKing(KING.toUpperCase())));
        IntStream.range(0, QUEEN_SIZE)
                .forEach(count -> pieces.add(generateQueen(QUEEN.toUpperCase())));
        IntStream.range(0, BISHOP_SIZE)
                .forEach(count -> pieces.add(generateBishop(BISHOP.toUpperCase())));
        IntStream.range(0, KNIGHT_SIZE)
                .forEach(count -> pieces.add(generateKnight(KNIGHT.toUpperCase())));
        IntStream.range(0, ROOK_SIZE)
                .forEach(count -> pieces.add(generateRook(ROOK.toUpperCase())));
        IntStream.range(0, PAWN_SIZE)
                .forEach(count -> pieces.add(generatePawn(PAWN.toUpperCase())));
        return new ArrayList<>(pieces);
    }

    private static Piece generateKing(String piece) {
        return King.from(piece, Position.emptyPosition());
    }

    private static Piece generateQueen(String piece) {
        return Queen.from(piece, Position.emptyPosition());
    }

    private static Piece generateBishop(String piece) {
        return Bishop.from(piece, Position.emptyPosition());
    }

    private static Piece generateKnight(String piece) {
        return Knight.from(piece, Position.emptyPosition());
    }

    private static Piece generateRook(String piece) {
        return Rook.from(piece, Position.emptyPosition());
    }

    private static Piece generatePawn(String piece) {
        return Pawn.from(piece, Position.emptyPosition());
    }
}
