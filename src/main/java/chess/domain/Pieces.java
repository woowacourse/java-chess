package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Pieces {
	private final List<Piece> pieces;

	public Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public List<Piece> getAlivePieces() {
		return pieces.stream().filter(Piece::isAlive).collect(Collectors.toList());
	}

	public List<Piece> getAlivePiecesByTeam(Team team) {
		return this.getAlivePieces().stream().filter(p -> p.isInTeam(team)).collect(Collectors.toList());
	}

	public Piece findByPosition(Position position) {
		return getAlivePieces().stream()
			.filter(p -> p.isSamePosition(position))
			.findFirst()
			.orElse(null);
	}
}
