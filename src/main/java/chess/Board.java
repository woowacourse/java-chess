package chess;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

public class Board {
	private final Map<Position, Piece> pieces;
	private Team turn;
	private boolean finished;

	public Board() {
		this.pieces = new HashMap<>();
		this.turn = Team.WHITE;
		this.finished = false;
	}

	public Board(Map<Position, Piece> pieces) {
		this.pieces = pieces;
		this.turn = Team.WHITE;
		this.finished = false;
	}

	public void start() {
		pieces.clear();
		initChessBoard(EIGHT, BLACK, SEVEN);
		initChessBoard(ONE, WHITE, TWO);
	}

	private void initChessBoard(Rank othersRank, Team teamColor, Rank pawnRank) {
		pieces.put(Position.of(A, othersRank), new Rook(teamColor));
		pieces.put(Position.of(B, othersRank), new Knight(teamColor));
		pieces.put(Position.of(C, othersRank), new Bishop(teamColor));
		pieces.put(Position.of(D, othersRank), new Queen(teamColor));
		pieces.put(Position.of(E, othersRank), new King(teamColor));
		pieces.put(Position.of(F, othersRank), new Bishop(teamColor));
		pieces.put(Position.of(G, othersRank), new Knight(teamColor));
		pieces.put(Position.of(H, othersRank), new Rook(teamColor));
		for (File value : File.values()) {
			pieces.put(Position.of(value, pawnRank), new Pawn(teamColor));
		}
	}

	public Map<Team, Double> status() {
		HashMap<Team, Double> collect = pieces.values().stream()
			.collect(groupingBy(Piece::getTeam, HashMap::new, summingDouble(Piece::getScore)));

		for (File file : File.values()) {
			Map<Team, Long> cnt = Arrays.stream(Rank.values())
				.map(rank -> pieces.get(Position.of(file, rank)))
				.filter(piece -> piece != null && piece.isPawn())
				.collect(groupingBy(Piece::getTeam, counting()));

			cnt.keySet().stream()
				.filter(team -> cnt.get(team) == 1)
				.forEach(team -> collect.put(team, collect.get(team) + 0.5));
		}
		return collect;
	}

	public void move(Position from, Position to) {
		checkPositions(from, to);
		Piece source = requireNonNullAndTurn(pieces.get(from));
		Piece target = pieces.get(to);
		validateSourceMovingRoute(from, to, source, target);

		pieces.remove(from);
		pieces.put(to, source);
		finishGameIfKingCaught(target);
		switchPlayerTurn();
		source.updateHasMoved();
	}

	private void validateSourceMovingRoute(Position from, Position to, Piece source, Piece target) {
		BoardOccupyState occupyState = BoardOccupyState.of(source, target);
		occupyState.checkMovable(this, source, from, to);
	}

	private void checkPositions(Position from, Position to) {
		if (Objects.isNull(from) || Objects.isNull(to)) {
			throw new IllegalArgumentException();
		}
	}

	private Piece requireNonNullAndTurn(Piece source) {
		if (Objects.isNull(source)) {
			throw new IllegalArgumentException("출발 값을 잘못 입력했습니다.");
		}
		if (!source.isRightTeam(turn)) {
			throw new IllegalArgumentException("현재 차례가 아닙니다.");
		}
		return source;
	}

	private void finishGameIfKingCaught(Piece target) {
		if (target != null && target.isKing()) {
			setGameEnd();
		}
	}

	private void switchPlayerTurn() {
		this.turn = turn.getOppositeTeam();
	}

	public boolean isExistAnyPieceAt(List<Position> traces) {
		return traces.stream()
			.anyMatch(pieces::containsKey);
	}

	public Piece getPiece(Position position) {
		return pieces.get(position);
	}

	public Map<Position, Piece> getPieces() {
		return Collections.unmodifiableMap(this.pieces);
	}

	public boolean checkGameEnd() {
		return !this.finished;
	}

	public void setGameEnd() {
		finished = true;
	}
}
