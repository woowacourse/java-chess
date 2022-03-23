package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class ChessBoard {
    private static final ChessBoardPosition ROOK_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE);
    private static final ChessBoardPosition KNIGHT_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.B, ChessBoardRow.ONE);
    private static final ChessBoardPosition BISHOP_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.C, ChessBoardRow.ONE);
    private static final ChessBoardPosition QUEEN_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.D, ChessBoardRow.ONE);

    private Map<ChessBoardPosition, Piece> value;

    public ChessBoard() {
        this.value = new HashMap<>();
        initializeFourPiecesOf(ROOK_INITIAL_POSITION, Rook::new);
        initializeFourPiecesOf(KNIGHT_INITIAL_POSITION, Knight::new);
        initializeFourPiecesOf(BISHOP_INITIAL_POSITION, Bishop::new);

        initializeTwoPiecesOf(QUEEN_INITIAL_POSITION, Queen::new);
    }

    private void initializeFourPiecesOf(ChessBoardPosition pieceInitialPosition, Supplier<Piece> pieceSupplier) {
        value.put(pieceInitialPosition, pieceSupplier.get());
        value.put(pieceInitialPosition.flipHorizontally(), pieceSupplier.get());
        value.put(pieceInitialPosition.flipVertically(), pieceSupplier.get());
        value.put(pieceInitialPosition.flipDiagonally(), pieceSupplier.get());
    }

    private void initializeTwoPiecesOf(ChessBoardPosition pieceInitialPosition, Supplier<Piece> pieceSupplier) {
        value.put(pieceInitialPosition, pieceSupplier.get());
        value.put(pieceInitialPosition.flipVertically(), pieceSupplier.get());
    }

    public Map<ChessBoardPosition, Piece> getValue() {
        return value;
    }
}
