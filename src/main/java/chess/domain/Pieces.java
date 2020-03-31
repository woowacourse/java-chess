package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Pieces {
	private static final int KING_DEFAULT_AMOUNT = 2;
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
			.count() == KING_DEFAULT_AMOUNT;
	}

	public Team teamWithAliveKing() {
		return this.getAlivePieces().stream()
			.filter(p -> p instanceof King)
			.findFirst()
			.orElseThrow(() -> new NullPointerException("킹이 한 명도 없습니다."))
			.getTeam();
	}

	public void killPiece(Piece piece) {
		pieces.values().remove(piece);
		piece.kill();
	}

	public void move(Position source, Position destination){
		Piece sourcePiece = findByPosition(source);
		sourcePiece.move(destination);
		pieces.remove(source);
		pieces.putIfAbsent(destination, sourcePiece);
	}
}
