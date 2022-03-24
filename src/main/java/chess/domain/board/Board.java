package chess.domain.board;

import static chess.domain.Camp.BLACK;
import static chess.domain.Camp.WHITE;

import chess.domain.Camp;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public final class Board {
    private static final Position ROOK_INITIAL_POSITION = new Position(Column.A, Row.ONE);
    private static final Position KNIGHT_INITIAL_POSITION = new Position(Column.B, Row.ONE);
    private static final Position BISHOP_INITIAL_POSITION = new Position(Column.C, Row.ONE);
    private static final Position QUEEN_INITIAL_POSITION = new Position(Column.D, Row.ONE);
    private static final Position KING_INITIAL_POSITION = new Position(Column.E, Row.ONE);
    private static final Row PAWN_INITIAL_ROW = Row.TWO;
    private static final int BLANK_INITIAL_START_ROW_INDEX = 2;
    private static final int BLANK_INITIAL_END_ROW_INDEX = 5;

    private final Map<Position, Piece> value;

    public Board() {
        this.value = new TreeMap<>();
        initializeFourPiecesOf(ROOK_INITIAL_POSITION, Rook::new);
        initializeFourPiecesOf(KNIGHT_INITIAL_POSITION, Knight::new);
        initializeFourPiecesOf(BISHOP_INITIAL_POSITION, Bishop::new);

        initializeTwoPiecesOf(QUEEN_INITIAL_POSITION, Queen::new);
        initializeTwoPiecesOf(KING_INITIAL_POSITION, King::new);

        initializePawn();
        initializeBlanks();
    }

    private void initializeFourPiecesOf(Position pieceInitialPosition,
                                        Function<Camp, Piece> pieceConstructor) {
        value.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipHorizontally(), pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
        value.put(pieceInitialPosition.flipDiagonally(), pieceConstructor.apply(BLACK));
    }

    private void initializeTwoPiecesOf(Position pieceInitialPosition, Function<Camp, Piece> pieceConstructor) {
        value.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
    }

    private void initializePawn() {
        for (Column column : Column.values()) {
            initializeTwoPiecesOf(new Position(column, PAWN_INITIAL_ROW), Pawn::new);
        }
    }

    private void initializeBlanks() {
        for (Column column : Column.values()) {
            initializeBlankColumn(column);
        }
    }

    private void initializeBlankColumn(Column column) {
        for (int i = BLANK_INITIAL_START_ROW_INDEX; i <= BLANK_INITIAL_END_ROW_INDEX; i++) {
            value.put(new Position(column, Row.values()[i]), null);
        }
    }

    public Map<Position, Piece> getValue() {
        return Map.copyOf(value);
    }
}
