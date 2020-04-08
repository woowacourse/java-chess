package chess.domain.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.exception.PieceMoveFailedException;

public class Board {
	private final Map<Coordinates, Piece> pieces;

	public Board(Map<Coordinates, Piece> pieces) {
		validateBoard(pieces);
		this.pieces = pieces;
	}

	private void validateBoard(Map<Coordinates, Piece> pieces) {
		if (Objects.isNull(pieces)) {
			throw new IllegalArgumentException("체스판이 존재하지 않습니다.");
		}
	}

	public Piece movePiece(Coordinates from, Coordinates to) {
		validatePieceExist(from);
		validateTargetIsNotAlly(from, to);
		validateRouteHasObstacle(from, to);
		Piece target = pieces.remove(from);
		pieces.put(to, target);
		return target;
	}

	private void validatePieceExist(Coordinates from) {
		if (isEmpty(from)) {
			throw new PieceMoveFailedException("이동할 말이 존재하지 않습니다");
		}
	}

	private void validateRouteHasObstacle(Coordinates from, Coordinates to) {
		if (canNotReachable(from, to)) {
			throw new PieceMoveFailedException("목적지까지 이동할 수 없습니다.");
		}
	}

	private void validateTargetIsNotAlly(Coordinates from, Coordinates to) {
		if (findPieceBy(to).isPresent() && isAlly(from, to)) {
			throw new PieceMoveFailedException("도착점에 같은 팀의 piece가 있습니다.");
		}
	}

	private boolean canNotReachable(Coordinates from, Coordinates to) {
		return findPieceBy(from)
				.map(piece -> piece.findMovableCoordinates(from, to))
				.filter(movableCoordinates -> !movableCoordinates.contains(to) || hasObstacle(movableCoordinates, to))
				.isPresent();
	}

	private boolean hasObstacle(List<Coordinates> movableCoordinates, Coordinates destination) {
		return movableCoordinates.stream()
				.anyMatch(position -> isObstacle(position) && !position.equals(destination));
	}

	public Optional<Piece> findPieceBy(Coordinates coordinates) {
		return Optional.ofNullable(pieces.get(coordinates));
	}

	public boolean isKingAliveOf(Color color) {
		return pieces.values()
				.stream()
				.anyMatch(piece -> isKingOf(piece, color));
	}

	private boolean isKingOf(Piece piece, Color color) {
		return piece.isTeamOf(color) && piece.isKing();
	}

	private boolean isAlly(Coordinates from, Coordinates to) {
		Piece source = findPieceBy(from)
				.orElseThrow(() -> new IllegalArgumentException("from 좌표에 해당하는 piece가 존재하지 않습니다."));
		Piece target = findPieceBy(to)
				.orElseThrow(() -> new IllegalArgumentException("to 좌표에 해당하는 piece가 존재하지 않습니다."));
		return source.isAlly(target);
	}

	private boolean isEmpty(Coordinates coordinates) {
		return Objects.isNull(pieces.get(coordinates));
	}

	private boolean isObstacle(Coordinates coordinates) {
		return Objects.nonNull(pieces.get(coordinates));
	}

	public Map<Coordinates, Piece> getPieces() {
		return new HashMap<>(pieces);
	}

	public List<Cell> getPiecesAsList() {
		return pieces.entrySet()
				.stream()
				.map(cell -> new Cell(cell.getKey(), cell.getValue()))
				.collect(Collectors.toList());
	}
}
