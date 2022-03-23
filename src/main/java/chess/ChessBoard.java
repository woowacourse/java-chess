package chess;

import static chess.Camp.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
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
    private static final ChessBoardPosition KING_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.E, ChessBoardRow.ONE);
    private static final ChessBoardRow PAWN_INITIAL_ROW = ChessBoardRow.TWO;

    private final Map<ChessBoardPosition, Piece> value;

    public ChessBoard() {
        this.value = new HashMap<>();
        initializeFourPiecesOf(ROOK_INITIAL_POSITION, Rook::new);
        initializeFourPiecesOf(KNIGHT_INITIAL_POSITION, Knight::new);
        initializeFourPiecesOf(BISHOP_INITIAL_POSITION, Bishop::new);

        initializeTwoPiecesOf(QUEEN_INITIAL_POSITION, Queen::new);
        initializeTwoPiecesOf(KING_INITIAL_POSITION, King::new);

        initializePawn();
    }

    private void initializeFourPiecesOf(ChessBoardPosition pieceInitialPosition,
                                         Function<Camp, Piece> pieceConstructor) {
        value.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipHorizontally(), pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
        value.put(pieceInitialPosition.flipDiagonally(), pieceConstructor.apply(BLACK));
    }

    private void initializeTwoPiecesOf(ChessBoardPosition pieceInitialPosition, Function<Camp, Piece> pieceConstructor) {
        value.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
    }

    private void initializePawn() {
        for (ChessBoardColumn column : ChessBoardColumn.values()){
            initializeTwoPiecesOf(new ChessBoardPosition(column, PAWN_INITIAL_ROW), Pawn::new);
        }
    }

    public Map<ChessBoardPosition, Piece> getValue() {
        return value;
    }
}
