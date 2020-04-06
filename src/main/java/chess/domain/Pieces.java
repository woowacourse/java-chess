package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Pieces {
	private final Map<Position, Piece> pieces;

	public Pieces(Map<Position, Piece> pieces) {
		this.pieces = new HashMap<>(pieces);
	}

	public List<Piece> getAlivePieces() {
		return pieces.values().stream()
			.filter(Piece::isAlive)
			.collect(Collectors.toList());
	}

	public List<Piece> getAlivePiecesByTeam(Team team) {
		return this.getAlivePieces().stream()
			.filter(p -> p.isInTeam(team))
			.collect(Collectors.toList());
	}

	public Piece findByPosition(Position position) {
		return pieces.get(position);
	}

	public boolean isBothKingAlive() {
		return this.getAlivePieces().stream()
			.filter(p -> p instanceof King)
			.count() == 2;
	}

	public Team teamWithAliveKing() {
		return this.getAlivePieces().stream()
			.filter(p -> p instanceof King)
			.findFirst()
			.orElseThrow(() -> new NullPointerException("킹이 한 명도 없습니다."))
			.getTeam();
	}

	public void move(Position source, Position destination) {
		Piece sourcePiece = this.pieces.get(source);
		sourcePiece.move(destination);
		this.pieces.put(destination, sourcePiece);
		this.pieces.remove(source);
	}

	public void kill(Piece destinationPiece) {
		destinationPiece.kill();
		this.pieces.values().remove(destinationPiece);
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}
}
