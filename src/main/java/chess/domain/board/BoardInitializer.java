package chess.domain.board;

import static chess.domain.Color.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import chess.domain.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class BoardInitializer {

	private static final Position ROOK_INITIAL_POSITION = Position.of(Column.A, Row.ONE);
	private static final Position KNIGHT_INITIAL_POSITION = Position.of(Column.B, Row.ONE);
	private static final Position BISHOP_INITIAL_POSITION = Position.of(Column.C, Row.ONE);
	private static final Position QUEEN_INITIAL_POSITION = Position.of(Column.D, Row.ONE);
	private static final Position KING_INITIAL_POSITION = Position.of(Column.E, Row.ONE);
	private static final Row PAWN_INITIAL_ROW = Row.TWO;
	private static final int BLANK_INITIAL_START_ROW_INDEX = 2;
	private static final int BLANK_INITIAL_END_ROW_INDEX = 5;

	public static Map<Position, Piece> createBoard() {
		final Map<Position, Piece> board = new TreeMap<>();
		initializeFourPiecesOf(board, ROOK_INITIAL_POSITION, Rook::new);
		initializeFourPiecesOf(board, KNIGHT_INITIAL_POSITION, Knight::new);
		initializeFourPiecesOf(board, BISHOP_INITIAL_POSITION, Bishop::new);

		initializeTwoPiecesOf(board, QUEEN_INITIAL_POSITION, Queen::new);
		initializeTwoPiecesOf(board, KING_INITIAL_POSITION, King::new);

		initializePawn(board);
		initializeBlanks(board);
		return board;
	}

	private static void initializeFourPiecesOf(Map<Position, Piece> board, Position pieceInitialPosition,
		Function<Color, Piece> pieceConstructor) {
		board.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
		board.put(pieceInitialPosition.flipHorizontally(), pieceConstructor.apply(WHITE));
		board.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
		board.put(pieceInitialPosition.flipDiagonally(), pieceConstructor.apply(BLACK));
	}

	private static void initializeTwoPiecesOf(Map<Position, Piece> board, Position pieceInitialPosition,
		Function<Color, Piece> pieceConstructor) {
		board.put(pieceInitialPosition, pieceConstructor.apply(WHITE));
		board.put(pieceInitialPosition.flipVertically(), pieceConstructor.apply(BLACK));
	}

	private static void initializePawn(Map<Position, Piece> board) {
		for (Column column : Column.values()) {
			initializeTwoPiecesOf(board, Position.of(column, PAWN_INITIAL_ROW), Pawn::new);
		}
	}

	private static void initializeBlanks(Map<Position, Piece> board) {
		for (Column column : Column.values()) {
			initializeBlankColumn(board, column);
		}
	}

	private static void initializeBlankColumn(Map<Position, Piece> board, Column column) {
		for (int i = BLANK_INITIAL_START_ROW_INDEX; i <= BLANK_INITIAL_END_ROW_INDEX; i++) {
			board.put(Position.of(column, Row.values()[i]), null);
		}
	}
}
