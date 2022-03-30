package chess.domain.board;

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
		board.put(Position.of(4, 4), piece);
		board.put(Position.of(3, 4), new Pawn(Team.BLACK));
		board.put(Position.of(5, 4), new Pawn(Team.BLACK));
		board.put(Position.of(4, 3), new Pawn(Team.BLACK));
		board.put(Position.of(4, 5), new Pawn(Team.BLACK));
		board.put(Position.of(5, 5), new Pawn(Team.BLACK));
		board.put(Position.of(3, 3), new Pawn(Team.BLACK));
		board.put(Position.of(3, 5), new Pawn(Team.BLACK));
		board.put(Position.of(5, 3), new Pawn(Team.BLACK));

		return board;
	}

	public static Map<Position, Piece> createSameColumnPawnBoard() {
		final Map<Position, Piece> board = createBlankBoard();
		board.put(Position.of(3, 4), new Pawn(Team.WHITE));
		board.put(Position.of(4, 4), new Pawn(Team.WHITE));
		board.put(Position.of(5, 4), new Pawn(Team.WHITE));
		return board;
	}

	public static Map<Position, Piece> createCatchKingBoard() {
		final Map<Position, Piece> board = createBlankBoard();
		board.put(Position.of(4, 4), new King(Team.WHITE));
		board.put(Position.of(5, 5), new King(Team.BLACK));
		return board;
	}
}
