package chess.domain.board;

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
import java.util.Optional;


public class Board {

	private final Map<Position, Piece> board;

	public Board() {
		this.board = new HashMap<>();
		initialBatchPiece();
	}

	public void move(Position source, Position target) {
		Piece piece = board.get(source);
		if (piece.isBlank()) {
			throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
		}
		Piece targetPiece = board.get(target);
		if (piece.isSameTeam(targetPiece)) {
			throw new IllegalArgumentException("같은 팀의 기물을 잡을 수 없습니다.");
		}

		piece.validateMovement(source, target);
		Direction direction = piece.getDirection(source, target);

		Position checkPosition = source;
		while (checkPosition != target) {
			Optional<Position> position = checkPosition.addDirection(direction);
			if (position.isEmpty()) {
				throw new IllegalArgumentException("체스 판의 범위를 벗어 났습니다.");
			}
			checkPosition = position.get();
			Piece currentPiece = board.get(checkPosition);

			if (checkPosition != target && !currentPiece.isBlank()) {
				throw new IllegalArgumentException("해당 위치로 기물을 옮길 수 없습니다.");
			}
		}
		board.put(target, piece);
		board.put(source, new Blank());
	}

	private void initialBatchPiece() {
		for (Position position : Position.getPositions()) {
			board.put(position, new Blank());
		}

		List<Piece> blackSpecials = initSpecialBuilder(Team.BLACK);
		List<Piece> whiteSpecials = initSpecialBuilder(Team.WHITE);
		for (int i = 0; i < 8; i++) {
			board.put(Position.of(8, i + 1), blackSpecials.get(i));
			board.put(Position.of(7, i + 1), new Pawn(Team.BLACK));

			board.put(Position.of(1, i + 1), whiteSpecials.get(i));
			board.put(Position.of(2, i + 1), new Pawn(Team.WHITE));
		}
	}

	private List<Piece> initSpecialBuilder(Team team) {
		List<Piece> pieces = new ArrayList<>();
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

	public boolean isBlank(Position position) {
		return board.get(position).isBlank();
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}
}
