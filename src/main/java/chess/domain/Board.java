package chess.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.exception.InvalidTurnException;

public class Board {
	private static Map<Team, Boolean> kingDead = new HashMap<>();

	private final Map<Position, Piece> board;

	static {
		kingDead.put(Team.BLACK, false);
		kingDead.put(Team.WHITE, false);
	}

	public Board(Map<Position, Piece> board) {
		this.board = new TreeMap<>(board);
	}

	public boolean isKingDead() {
		return kingDead.entrySet()
			.stream()
			.anyMatch(Map.Entry::getValue);
	}

	public void move(Position from, Position to, Turn turn) {
		Piece source = hasPieceIn(from);
		checkTurn(turn, source);
		source = source.move(from, to, getTeamBoard());
		board.remove(from);
		checkKingDead(to);
		board.put(to, source);
	}

	private void checkTurn(Turn turn, Piece source) {
		if (!source.isTurn(turn)) {
			throw new InvalidTurnException("해당 플레이어의 턴이 아닙니다.");
		}
	}

	private void checkKingDead(Position to) {
		Piece target = board.get(to);
		if (Objects.nonNull(target) && target.isKing()) {
			kingDead.put(board.get(to).getTeam(), true);
		}
	}

	private Piece hasPieceIn(Position from) {
		Piece piece = board.get(from);
		if (Objects.isNull(piece)) {
			throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
		}
		return piece;
	}

	public List<Piece> findPiecesByTeam(Team team) {
		return board.values().stream()
			.filter(value -> value.getTeam() == team)
			.collect(Collectors.toList());
	}

	public Map<Position, Piece> getBoard() {
		return Collections.unmodifiableMap(board);
	}

	public Map<Position, Team> getTeamBoard() {
		return board.entrySet().stream()
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				entry -> entry.getValue().getTeam()
			));
	}
}
