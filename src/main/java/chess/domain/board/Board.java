package chess.domain.board;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.dto.PieceDto;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {
	private final Map<Position, Piece> pieces;

	public Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public static Board of(List<PieceDto> pieces) {
		return new Board(pieces.stream()
			.collect(Collectors.toMap(PieceDto::getPosition, PieceDto::getPiece)));
	}

	public boolean isNotEmptyPosition(Position position) {
		return pieces.get(position) != null;
	}

	public Piece findPieceBy(Position position) {
		return pieces.get(position);
	}

	public void movePiece(Position from, Position to) {
		Piece target = pieces.remove(from);

		pieces.put(to, target);
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}

	public boolean isKingAliveOf(Color color) {
		return pieces.values().stream()
			.anyMatch(piece -> isKingOf(color, piece));
	}

	private boolean isKingOf(Color color, Piece piece) {
		return piece.isSameColor(color) && piece instanceof King;
	}

	public void deleteAll() {

	}
}