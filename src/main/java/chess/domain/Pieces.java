package chess.domain;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Pieces {
	private static final int KING_DEFAULT_AMOUNT = 2;
	private final List<Piece> pieces;

	public Pieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public List<Piece> getAlivePieces() {
		return pieces.stream()
				.filter(Piece::isAlive)
				.collect(Collectors.toList());
	}

	public Pieces getAlivePiecesByTeam(Team team) {
		return new Pieces(this.getAlivePieces().stream()
				.filter(p -> p.isInTeam(team))
				.collect(Collectors.toList()));
	}

	public Piece findByPosition(Position position) {
		return getAlivePieces().stream()
			.filter(p -> p.isSamePosition(position))
			.findFirst()
			.orElse(null);
	}

	public boolean isBothKingAlive() {
		return this.getAlivePieces().stream()
			.filter(p -> p instanceof King)
			.count() == KING_DEFAULT_AMOUNT;
	}

	public Team teamWithAliveKing() {
		return this.getAlivePieces().stream()
			.filter(p -> p instanceof King)
			.findFirst()
			.orElseThrow(() -> new NullPointerException("킹이 한 명도 없습니다."))
			.getTeam();
	}
}
