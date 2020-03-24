package chess.piece;

import java.util.ArrayList;
import java.util.List;

import static chess.piece.PieceNameType.*;

public class PieceSet {
    private static final int PAWN_COUNT = 8;
    private static final int ROOK_COUNT = 2;
    private static final int KNIGHT_COUNT = 2;
    private static final int BISHOP_COUNT = 2;
    private static final int KING_COUNT = 1;
    private static final int QUEEN_COUNT = 1;

    private final List<Piece> pieces;

    public PieceSet(boolean isBlack) {
        pieces = new ArrayList<>();
        makePieceSet(isBlack);
    }

    private void makePieceSet(boolean isBlack) {
        makePieceSet(Piece.of(PAWN, isBlack), PAWN_COUNT);
        makePieceSet(Piece.of(ROOK, isBlack), ROOK_COUNT);
        makePieceSet(Piece.of(KNIGHT, isBlack), KNIGHT_COUNT);
        makePieceSet(Piece.of(BISHOP, isBlack), BISHOP_COUNT);
        makePieceSet(Piece.of(KING, isBlack), KING_COUNT);
        makePieceSet(Piece.of(QUEEN, isBlack), QUEEN_COUNT);
    }

    private void makePieceSet(Piece piece, int size) {
        for (int i = 0; i < size; i++) {
            pieces.add(piece);
        }
    }
}
