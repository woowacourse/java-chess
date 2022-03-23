package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class ChessBoard {
    private static final ChessBoardPosition BISHOP_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.C, ChessBoardRow.ONE);
    private static final ChessBoardPosition ROOK_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE);

    private Map<ChessBoardPosition, Piece> value;

    public ChessBoard() {
        this.value = new HashMap<>();
        initializePiece( ROOK_INITIAL_POSITION, Rook::new);
        initializePiece( BISHOP_INITIAL_POSITION, Bishop::new);
    }

    private void initializePiece(ChessBoardPosition pieceInitialPosition, Supplier<Piece> pieceSupplier) {
        value.put(pieceInitialPosition, pieceSupplier.get());
        value.put(pieceInitialPosition.flipHorizontally(), pieceSupplier.get());
        value.put(pieceInitialPosition.flipVertically(), pieceSupplier.get());
        value.put(pieceInitialPosition.flipDiagonally(), pieceSupplier.get());
    }

    public Map<ChessBoardPosition, Piece> getValue() {
        return value;
    }
}
