package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
	
	private BoardFactory() {
		throw new AssertionError();
	}

	public static Map<Position, Piece> initiate() {
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
		createInitSpecialPieces(board, EIGHT, Team.BLACK);
		createInitPawn(board, SEVEN, Team.BLACK);
	}

	private static void addWhitePieces(final Map<Position, Piece> board) {
		createInitSpecialPieces(board, ONE, Team.WHITE);
		createInitPawn(board, TWO, Team.WHITE);
	}

	private static void createInitSpecialPieces(final Map<Position, Piece> board, final Rank rank,
												final Team team) {
		board.put(Position.of(rank, A), new Rook(team));
		board.put(Position.of(rank, B), new Knight(team));
		board.put(Position.of(rank, C), new Bishop(team));
		board.put(Position.of(rank, D), new Queen(team));
		board.put(Position.of(rank, E), new King(team));
		board.put(Position.of(rank, F), new Bishop(team));
		board.put(Position.of(rank, G), new Knight(team));
		board.put(Position.of(rank, H), new Rook(team));
	}

	private static void createInitPawn(final Map<Position, Piece> board, final Rank rank,
									   final Team team) {
		for (File file : File.values()) {
			board.put(Position.of(rank, file), new Pawn(team));
		}
	}
}
