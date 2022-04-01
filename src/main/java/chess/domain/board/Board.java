package chess.domain.board;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.Team;

public class Board {
	public static final int BOTH_KING_ALIVE = 2;
	
	private final Map<Coordinate, Piece> value;

	private Board(Map<Coordinate, Piece> value) {
		this.value = value;
	}

	public static Board create() {
		return new Board(InitialBoard.init());
	}

	public Board move(String from, String to) {
		return move(Coordinate.of(from), Coordinate.of(to));
	}

	public Board move(Coordinate from, Coordinate to) {
		Piece piece = findPiece(from);

		if (!piece.isMovable(value, from, to)) {
			throw new IllegalArgumentException("움직일 수 없습니다.");
		}

		swapPiece(from, to);
		return new Board(value);
	}

	private Piece findPiece(Coordinate coordinate) {
		Piece piece = value.get(coordinate);
		if (piece.isEmpty()) {
			throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
		}
		return piece;
	}

	private void swapPiece(Coordinate from, Coordinate to) {
		Piece piece = findPiece(from);
		value.put(from, new Empty(Symbol.EMPTY, Team.NONE));
		value.put(to, piece);
	}

	public Piece findByCoordinate(Coordinate coordinate) {
		return value.get(coordinate);
	}

	public boolean isBothKingAlive() {
		return value.values()
			.stream()
			.filter(Piece::isKing)
			.count() == BOTH_KING_ALIVE;
	}

	public Map<Coordinate, Piece> getValue() {
		return value;
	}
}
