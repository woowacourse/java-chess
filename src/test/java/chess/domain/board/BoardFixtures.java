package chess.domain.board;

import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.THREE;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;

public class BoardFixtures {

	public static Map<Position, Piece> createBlankBoard() {
		final Map<Position, Piece> board = new HashMap<>();
		for (Position position : Position.getPositions()) {
			board.put(position, new Blank());
		}
		return board;
	}

	public static Map<Position, Piece> createBoardWithBlackBlocking(Piece piece) {
		final Map<Position, Piece> board = createBlankBoard();
		board.put(Position.of(FOUR, D), piece);
		board.put(Position.of(THREE, D), new Pawn(Team.BLACK));
		board.put(Position.of(FIVE, D), new Pawn(Team.BLACK));
		board.put(Position.of(FOUR, C), new Pawn(Team.BLACK));
		board.put(Position.of(FOUR, E), new Pawn(Team.BLACK));
		board.put(Position.of(FIVE, E), new Pawn(Team.BLACK));
		board.put(Position.of(THREE, C), new Pawn(Team.BLACK));
		board.put(Position.of(THREE, E), new Pawn(Team.BLACK));
		board.put(Position.of(FIVE, C), new Pawn(Team.BLACK));

		return board;
	}

	public static Map<Position, Piece> createSameFilePawnBoard() {
		final Map<Position, Piece> board = createBlankBoard();
		board.put(Position.of(THREE, D), new Pawn(Team.WHITE));
		board.put(Position.of(FOUR, D), new Pawn(Team.WHITE));
		board.put(Position.of(FIVE, D), new Pawn(Team.WHITE));
		return board;
	}

	public static Map<Position, Piece> createCatchKingBoard() {
		final Map<Position, Piece> board = createBlankBoard();
		board.put(Position.of(FOUR, D), new King(Team.WHITE));
		board.put(Position.of(FIVE, E), new King(Team.BLACK));
		return board;
	}
}
