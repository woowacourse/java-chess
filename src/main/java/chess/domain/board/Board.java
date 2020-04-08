package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Path;
import chess.domain.position.Position;

public class Board {
	private static final int TEAM_COUNT = 2;

	private final Map<Position, Piece> board;

	private Board(Map<Position, Piece> board) {
		this.board = board;
	}

	public static Board of(Map<Position, Piece> board) {
		fillEmpty(board);
		return new Board(board);
	}

	private static void fillEmpty(Map<Position, Piece> board) {
		Position.getPositions()
			.stream()
			.filter(position -> board.get(position) == null)
			.forEach(position -> board.put(position, new Empty(position)));
	}

	public static Board of(List<Piece> pieces) {
		return of(pieces.stream()
			.collect(toMap(
				Piece::getPosition,
				Function.identity())
			));
	}

	public void verifyMove(Position from, Position to, Team current) {
		if (isEnd()) {
			throw new IllegalArgumentException("게임 끝");
		}

		Piece piece = board.get(from);
		Piece target = board.get(to);

		if (piece.isNotSameTeam(current)) {
			throw new IllegalArgumentException("아군 기물의 위치가 아닙니다.");
		}
		if (hasPieceIn(Path.of(from, to)) || piece.canNotMoveTo(target)) {
			throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
		}
	}

	private boolean hasPieceIn(Path path) {
		return path.toList()
			.stream()
			.anyMatch(key -> board.get(key).isObstacle());
	}

	private boolean isEnd() {
		return board.values()
			.stream()
			.filter(Piece::hasToAlive)
			.count() != TEAM_COUNT;
	}
}
