package chess.domain.board;

import static chess.domain.board.Position.MAX_POSITION;
import static chess.domain.board.Position.MIN_POSITION;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialBoard {

	public static final int INITIAL_BLACK_PAWN_ROW = 7;
	public static final int INITIAL_WHITE_PAWN_ROW = 2;
	private static final int INITIAL_BLACK_ROW = 8;
	private static final int INITIAL_WHITE_ROW = 1;

	private InitialBoard() {
		throw new AssertionError();
	}

	public static Map<Position, Piece> createBoard() {
		final Map<Position, Piece> board = createBlankBoard();
		return addPieces(board);
	}

	private static Map<Position, Piece> createBlankBoard() {
		final Map<Position, Piece> board = new HashMap<>();
		for (Position position : Position.getPositions()) {
			board.put(position, new Blank());
		}
		return board;
	}

	private static Map<Position, Piece> addPieces(final Map<Position, Piece> board) {
		addBlackPieces(board);
		addWhitePieces(board);
		return board;
	}

	private static void addBlackPieces(final Map<Position, Piece> board) {
		List<Piece> specialPieces = createInitSpecialPieces(Team.BLACK);
		for (int i = MIN_POSITION; i <= MAX_POSITION; i++) {
			board.put(Position.of(INITIAL_BLACK_ROW, i), specialPieces.get(i));
			board.put(Position.of(INITIAL_BLACK_PAWN_ROW, i), new Pawn(Team.BLACK));
		}
	}

	private static void addWhitePieces(final Map<Position, Piece> board) {
		List<Piece> whiteSpecials = createInitSpecialPieces(Team.WHITE);
		for (int i = MIN_POSITION; i <= MAX_POSITION; i++) {
			board.put(Position.of(INITIAL_WHITE_ROW, i), whiteSpecials.get(i));
			board.put(Position.of(INITIAL_WHITE_PAWN_ROW, i), new Pawn(Team.WHITE));
		}
	}

	private static List<Piece> createInitSpecialPieces(Team team) {
		List<Piece> pieces = new ArrayList<>();
		pieces.add(new Blank());
		pieces.add(new Rook(team));
		pieces.add(new Knight(team));
		pieces.add(new Bishop(team));
		pieces.add(new Queen(team));
		pieces.add(new King(team));
		pieces.add(new Bishop(team));
		pieces.add(new Knight(team));
		pieces.add(new Rook(team));
		return pieces;
	}
}
