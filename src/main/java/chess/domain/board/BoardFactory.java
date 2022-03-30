package chess.domain.board;

import static chess.domain.Camp.BLACK;
import static chess.domain.Camp.WHITE;

import chess.domain.Camp;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.NullPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class BoardFactory {
    private static final Position ROOK_INITIAL_POSITION = Position.of(Column.A, Row.ONE);
    private static final Position KNIGHT_INITIAL_POSITION = Position.of(Column.B, Row.ONE);
    private static final Position BISHOP_INITIAL_POSITION = Position.of(Column.C, Row.ONE);
    private static final Position QUEEN_INITIAL_POSITION = Position.of(Column.D, Row.ONE);
    private static final Position KING_INITIAL_POSITION = Position.of(Column.E, Row.ONE);
    private static final Row PAWN_INITIAL_ROW = Row.TWO;
    private static final int BLANK_INITIAL_START_ROW_INDEX = 2;
    private static final int BLANK_INITIAL_END_ROW_INDEX = 5;

    private BoardFactory() {
    }

    public static Map<Position, Piece> generate() {
        final TreeMap<Position, Piece> value = new TreeMap<>();
        initializeEveryFourPiece(value);
        initializeEveryTwoPiece(value);
        initializePawn(value);
        initializeBlanks(value);
        return value;
    }

    private static void initializeEveryFourPiece(final TreeMap<Position, Piece> value) {
        initializeFourPiecesOf(value, ROOK_INITIAL_POSITION, Rook::new);
        initializeFourPiecesOf(value, KNIGHT_INITIAL_POSITION, Knight::new);
        initializeFourPiecesOf(value, BISHOP_INITIAL_POSITION, Bishop::new);
    }

    private static void initializeFourPiecesOf(
        final TreeMap<Position, Piece> value,
        Position pieceInitialPosition,
        Function<Camp, Piece> pieceConstructor) {
        value.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipHorizontally(), pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
        value.put(pieceInitialPosition.flipDiagonally(), pieceConstructor.apply(BLACK));
    }

    private static void initializeEveryTwoPiece(final TreeMap<Position, Piece> value) {
        initializeTwoPiecesOf(value, QUEEN_INITIAL_POSITION, Queen::new);
        initializeTwoPiecesOf(value, KING_INITIAL_POSITION, King::new);
    }

    private static void initializeTwoPiecesOf(
        final TreeMap<Position, Piece> value,
        Position pieceInitialPosition, Function<Camp, Piece> pieceConstructor) {
        value.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
        value.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
    }

    private static void initializePawn(final TreeMap<Position, Piece> value) {
        for (Column column : Column.values()) {
            initializeTwoPiecesOf(value, Position.of(column, PAWN_INITIAL_ROW), Pawn::new);
        }
    }

    private static void initializeBlanks(final TreeMap<Position, Piece> value) {
        for (Column column : Column.values()) {
            initializeBlankColumn(value, column);
        }
    }

    private static void initializeBlankColumn(
        final TreeMap<Position, Piece> value,
        Column column) {
        for (int i = BLANK_INITIAL_START_ROW_INDEX; i <= BLANK_INITIAL_END_ROW_INDEX; i++) {
            value.put(Position.of(column, Row.values()[i]), new NullPiece(null));
        }
    }
}
